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
    private lateinit var authenticationService: AuthenticationService

    @PostMapping("signup")
    fun registerUser(@RequestBody registerUserRequest: RegisterUserRequest): ResponseEntity<*>{
        return authenticationService.registerNewUser(registerUserRequest)
    }

    @PostMapping("login")
    fun authenticateUser(@RequestBody logInRequest: LogInRequest):ResponseEntity<*>{
        return authenticationService.authenticate(logInRequest)
    }
}