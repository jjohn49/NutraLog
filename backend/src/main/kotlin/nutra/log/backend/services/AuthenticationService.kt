package nutra.log.backend.services

import nutra.log.backend.models.User
import nutra.log.backend.requests.LogInRequest
import nutra.log.backend.requests.RegisterUserRequest
import nutra.log.backend.responses.LogInResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
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

    fun authenticate(logInRequest: LogInRequest): ResponseEntity<LogInResponse.LogInBody>{

        return try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    logInRequest.username,
                    logInRequest.password
                )
            )

            val user: User = userService.findById(logInRequest.username).orElseThrow()
            val accessToken = tokenService.createToken(user)
            ResponseEntity.ok(LogInResponse.LogInBody(accessToken, user))
        }catch (e: NoSuchElementException){
            ResponseEntity.notFound().build()
        } catch (e: Exception){
            ResponseEntity.badRequest().build()
        }

    }

    fun registerNewUser(registerUserRequest: RegisterUserRequest) : ResponseEntity<*>{
        val hashedPassword = passwordEncoder.encode(registerUserRequest.password)

        val newUser = User(registerUserRequest.id, email = registerUserRequest.email, password = hashedPassword)

        return userService.addUser(newUser)
    }

}