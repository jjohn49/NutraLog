package nutra.log.backend.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService: UserDetailsService {

    @Autowired
    private lateinit var userService: UserService

    override fun loadUserByUsername(username: String): UserDetails {
            return userService.findById(username).get().mapToDetails()
    }

    private fun nutra.log.backend.models.User.mapToDetails(): UserDetails {
        return User.builder().username(this.id).password(this.password).roles("USER").build()
    }
}