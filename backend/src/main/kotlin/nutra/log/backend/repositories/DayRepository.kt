package nutra.log.backend.repositories

import nutra.log.backend.models.Day
import nutra.log.backend.models.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDate
import java.util.*

interface DayRepository : MongoRepository<Day, String> {

    fun findAllByUserId(userId: String): Optional<List<Day>>

    fun existsByDateAndUserId(date: LocalDate, userId: String): Boolean
    fun findByDateAndUserId(date: LocalDate, userId: String): Optional<Day>

    fun findDayByDate(date: LocalDate): Optional<Day>

    fun findDayByDateAndUserId(date: LocalDate, userId: String): Optional<Day>

    fun findDayByDateAndId(date: LocalDate, id: String): Optional<Day>

    fun findDayByIdAndUserId(id: String, userId: String): Optional<Day>
}