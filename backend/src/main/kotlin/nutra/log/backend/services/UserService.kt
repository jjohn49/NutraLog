package nutra.log.backend.services

import nutra.log.backend.models.User
import nutra.log.backend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired val repo: UserRepository, @Autowired val dayService: DayService) {
    
    fun addUser(user: User){
        repo.insert(user)
    }
}