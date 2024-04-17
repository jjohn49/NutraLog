package nutra.log.backend.services

import nutra.log.backend.models.Day
import nutra.log.backend.models.User
import nutra.log.backend.repositories.DayRepository
import nutra.log.backend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.update
import org.springframework.stereotype.Service

@Service
class DayService(@Autowired val dayRepo: DayRepository, @Autowired val userRepo: UserRepository) {

    fun addDay(day: Day){
        dayRepo.insert(day)

        val user = userRepo.findById(day.userId.toString())

        if(user.isPresent){
            user.get().days.add(day.id)
            userRepo.save(user.get())
            println("Saved ${user} to Mongo")
        }else{
            println("User with the object id of ${day.userId.toString()} was not found")
        }

    }
}