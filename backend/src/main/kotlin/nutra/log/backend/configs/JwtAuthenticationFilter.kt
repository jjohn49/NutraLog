package nutra.log.backend.configs

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import nutra.log.backend.models.User
import nutra.log.backend.services.CustomUserDetailService
import nutra.log.backend.services.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter():OncePerRequestFilter() {

    @Autowired
    private lateinit var userDetailService: CustomUserDetailService

    @Autowired
    private lateinit var tokenService: TokenService

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")

        if(authHeader.doesNotContainToken()){
            filterChain.doFilter(request,response)
            return
        }

        val token = authHeader!!

        val user: User = tokenService.parseToken(token)

        if(SecurityContextHolder.getContext().authentication == null){
            val foundUser = userDetailService.loadUserByUsername(user.id)

            updateContext(foundUser, request)

            filterChain.doFilter(request,response)
        }

    }

    private fun updateContext(foundUser: UserDetails, request: HttpServletRequest){
        val authToken = UsernamePasswordAuthenticationToken(foundUser,null, listOf(SimpleGrantedAuthority("USER")))

        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authToken
    }

    private fun String?.doesNotContainToken(): Boolean = this == null

    private fun String.extractToken(): String =
        this.substringAfter("Bearer")
}