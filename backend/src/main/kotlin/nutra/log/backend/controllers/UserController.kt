package nutra.log.backend.controllers

import com.fasterxml.jackson.databind.util.JSONPObject
import netscape.javascript.JSObject
import nutra.log.backend.models.User
import nutra.log.backend.repositories.UserRepository
import org.bson.json.JsonObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController(@Autowired val repo: UserRepository) {


    @PostMapping("add")
    fun addUser(@RequestBody user: User): Any{
        repo.insert(user)
        return user
    }

}