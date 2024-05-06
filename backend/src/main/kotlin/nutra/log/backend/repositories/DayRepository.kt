package nutra.log.backend.repositories

import nutra.log.backend.models.Day
import nutra.log.backend.models.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface DayRepository : MongoRepository<Day, String> {

    fun findAllByUserId(userId: ObjectId): List<Day>

    fun findDayByDate(date: Date): Day

    fun findDayByDateAndId(date: Date, id: ObjectId): Day
}