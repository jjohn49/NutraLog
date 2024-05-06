package nutra.log.backend.services

import nutra.log.backend.exceptions.UserAlreadyExistsException
import nutra.log.backend.models.User
import nutra.log.backend.requests.LogInRequest
import nutra.log.backend.requests.RegisterUserRequest
import nutra.log.backend.responses.BackendResponse
import nutra.log.backend.responses.SuccessfulLoginResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.RequestEntity
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

    fun authenticate(logInRequest: LogInRequest): ResponseEntity<*>{
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                logInRequest.username,
                logInRequest.password
            )
        )

        val user = userDetailService.loadUserByUsername(logInRequest.username)
        val accessToken = tokenService.createToken(userService.findById(logInRequest.username))

        return ResponseEntity.ok(SuccessfulLoginResponse(accessToken))
    }

    fun registerNewUser(registerUserRequest: RegisterUserRequest) : ResponseEntity<*>{
        val hashedPassword = passwordEncoder.encode(registerUserRequest.password)

        val newUser = User(registerUserRequest.id, email = registerUserRequest.email, password = hashedPassword)

        try{
            newUser.let { userService.addUser(user = newUser) }
        }catch (e: UserAlreadyExistsException){
            return ResponseEntity.ok(BackendResponse(false,e.message.toString()))
        }


        return ResponseEntity.ok<Any>(BackendResponse(true,"Created New User For Email: ${registerUserRequest.email}"))
    }

}