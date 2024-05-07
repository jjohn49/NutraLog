package nutra.log.backend.controllers

import com.fasterxml.jackson.databind.util.JSONPObject
import netscape.javascript.JSObject
import nutra.log.backend.models.User
import nutra.log.backend.repositories.UserRepository
import nutra.log.backend.requests.SetUserGoalsRequest
import nutra.log.backend.requests.UserRequest
import nutra.log.backend.responses.BackendResponse
import nutra.log.backend.responses.UserResponse
import nutra.log.backend.services.TokenService
import nutra.log.backend.services.UserService
import org.apache.tomcat.util.http.parser.Authorization
import org.bson.json.JsonObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("get")
    fun getUser(authentication: Authentication):ResponseEntity<*>{
        val user = userService.findById(authentication.name)
        return ResponseEntity.ok<Any>(UserResponse(true, user, "Successfully Authenticated User"))
    }

    @PutMapping("set/goals")
    fun setUserGoals(authentication: Authentication, @RequestBody setUserGoalsRequest: SetUserGoalsRequest):ResponseEntity<*>{
        val user = userService.findById(authentication.name)

        user.userGoals = setUserGoalsRequest.userGoal

        return ResponseEntity.ok(UserResponse(true,user,"User's goal was successfully updated."))
    }

}