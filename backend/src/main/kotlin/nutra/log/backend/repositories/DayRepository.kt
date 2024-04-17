package nutra.log.backend.repositories

import nutra.log.backend.models.Day
import nutra.log.backend.models.User
import org.springframework.data.mongodb.repository.MongoRepository

interface DayRepository : MongoRepository<Day, String> {
}