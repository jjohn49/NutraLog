package nutra.log.backend.services

import nutra.log.backend.models.User
import nutra.log.backend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(@Autowired val repo: UserRepository, @Autowired val dayService: DayService) {

    fun findById(userId:String): Optional<User>{
        return repo.findById(userId)
    }
    fun addUser(user: User){
        repo.insert(user)
    }
}