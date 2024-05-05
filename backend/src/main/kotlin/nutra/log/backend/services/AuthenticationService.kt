package nutra.log.backend.services

import nutra.log.backend.requests.LogInRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userDetailService: CustomUserDetailService

    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    fun authenticate(logInRequest: LogInRequest): String{
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                logInRequest.username,
                logInRequest.password
            )
        )

        val user = userDetailService.loadUserByUsername(logInRequest.username)
        val accessToken = tokenService.createToken(userService.findById(logInRequest.username))

        return accessToken
    }

}