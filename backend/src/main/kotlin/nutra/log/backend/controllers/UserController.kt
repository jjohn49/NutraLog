package nutra.log.backend.controllers

import nutra.log.backend.models.User
import nutra.log.backend.models.UserGoal
import nutra.log.backend.requests.SetUserGoalsRequest
import nutra.log.backend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("get")
    fun getUser(authentication: Authentication):ResponseEntity<User>{
        return userService.find(authentication.name)
    }

    @PutMapping("set/goals")
    fun setUserGoals(authentication: Authentication, @RequestBody setUserGoalsRequest: SetUserGoalsRequest):ResponseEntity<UserGoal>{
        val user = userService.find(authentication.name)

        //TODO: Need to hook this up to a service because rn it is doing nothing
        //user.userGoals = setUserGoalsRequest.userGoal

        return ResponseEntity.ok(setUserGoalsRequest.userGoal)
    }

}