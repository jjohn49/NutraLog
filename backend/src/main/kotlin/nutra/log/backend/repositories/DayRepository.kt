package nutra.log.backend.repositories

import nutra.log.backend.models.Day
import org.springframework.data.mongodb.repository.MongoRepository

interface DayRepository : MongoRepository<Day, String> {
}