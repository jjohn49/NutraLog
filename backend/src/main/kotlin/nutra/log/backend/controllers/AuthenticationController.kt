package nutra.log.backend.controllers

import nutra.log.backend.models.User
import nutra.log.backend.requests.LogInRequest
import nutra.log.backend.requests.RegisterUserRequest
import nutra.log.backend.responses.BackendResponse
import nutra.log.backend.responses.SuccessfulLoginResponse
import nutra.log.backend.services.AuthenticationService
import nutra.log.backend.services.TokenService
import nutra.log.backend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("auth")
class AuthenticationController {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var authenticationService: AuthenticationService

    @PostMapping("signup")
    fun registerUser(@RequestBody registerUserRequest: RegisterUserRequest): ResponseEntity<*>{
        val hashedPassword = passwordEncoder.encode(registerUserRequest.password)

        val newUser = User(registerUserRequest.id, email = registerUserRequest.email, password = hashedPassword)

        newUser.let { userService.addUser(user = newUser) }

        return ResponseEntity.ok<Any>(BackendResponse(true,"Created New User For Email: ${registerUserRequest.email}"))
    }

    @PostMapping("login")
    fun authenticateUser(@RequestBody logInRequest: LogInRequest):ResponseEntity<*>{
        val token = authenticationService.authenticate(logInRequest)
        return ResponseEntity.ok(SuccessfulLoginResponse(token))
    }
}