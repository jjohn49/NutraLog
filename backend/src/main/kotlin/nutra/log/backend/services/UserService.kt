package nutra.log.backend.services

import nutra.log.backend.exceptions.UserAlreadyExistsException
import nutra.log.backend.models.User
import nutra.log.backend.repositories.UserRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(@Autowired val repo: UserRepository, @Autowired val dayService: DayService) {

    fun findById(userId:String): User{
        return repo.findById(userId).orElseThrow()
    }
    fun addUser(user: User) {
        if(repo.existsById(user.id)){
            throw UserAlreadyExistsException()
        }
        repo.insert(user)
    }

    fun addDayToUser(userId: String, dayId:ObjectId){
        val user = this.findById(userId)

        user.days.add(dayId)

        repo.save(user)
    }
}