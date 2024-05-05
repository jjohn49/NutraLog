package nutra.log.backend.services

import nutra.log.backend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class CustomUserDetailService: UserDetailsService {

    @Autowired
    private lateinit var userService: UserService

    override fun loadUserByUsername(username: String): UserDetails =  userService.findById(username).mapToDetails()


    private fun nutra.log.backend.models.User.mapToDetails(): UserDetails {
        return User.builder().username(this.id).password(this.password).roles("USER").build()
    }
}