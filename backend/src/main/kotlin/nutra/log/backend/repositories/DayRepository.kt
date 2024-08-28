package nutra.log.backend.repositories

import nutra.log.backend.models.Day
import nutra.log.backend.models.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDate
import java.util.*

interface DayRepository : MongoRepository<Day, String> {

    fun findAllByUserId(userId: String): List<Day>

    fun findDayByDate(date: LocalDate): Day

    fun findDayByDateAndUserId(date: LocalDate, userId: String): Day

    fun findDayByDateAndId(date: LocalDate, id: String): Day
}