package nutra.log.backend.repositories

import nutra.log.backend.models.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String>{

}