package nutra.log.backend.services

import nutra.log.backend.models.Day
import nutra.log.backend.models.User
import nutra.log.backend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(@Autowired val repo: UserRepository) {

    fun find(userId:String): ResponseEntity<User>{
        return try {
            val user = repo.findById(userId).orElseThrow()
            ResponseEntity.ok(user)
        } catch (e: NoSuchElementException){
            ResponseEntity.notFound().build()
        }
    }

    fun findById(userId:String): Optional<User>{
        return repo.findById(userId)
    }
    fun addUser(user: User): ResponseEntity<User> {
        if(repo.existsById(user.id)){
            return ResponseEntity(HttpStatusCode.valueOf(409))
        }
        repo.insert(user)
        return ResponseEntity(user,HttpStatusCode.valueOf(201))
    }

    fun addDayToUser(authentication: Authentication, day: Day):ResponseEntity<Day>{

        return try {
            val user: User = repo.findById(authentication.name).orElseThrow()

            if(user.days.contains(day)){
                ResponseEntity.badRequest().build()
            }else {
                user.days += day
                repo.save(user)
                ResponseEntity(day, HttpStatusCode.valueOf(201))
            }
        }catch (e: NoSuchElementException){
            return ResponseEntity.notFound().build()
        }

    }
}