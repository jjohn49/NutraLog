package nutra.log.backend.services

import nutra.log.backend.models.*
import nutra.log.backend.repositories.DayRepository
import nutra.log.backend.repositories.FoodRepository
import nutra.log.backend.repositories.UserRepository
import nutra.log.backend.requests.AddFootToDayRequest
import nutra.log.backend.responses.GenericResponse
import nutra.log.backend.responses.GetDayResponse
import org.bson.types.ObjectId

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.Exception

@Service
class DayService(@Autowired val dayRepo: DayRepository) {


    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var foodFactsService: OpenFoodFactsService

    fun addDay(authentication: Authentication, day: Day): ResponseEntity<GetDayResponse>{
        return try {
            dayRepo.insert(day)
            val user = userService.findById(authentication.name)
            userService.addDayToUser(user.id, day.id)
            ResponseEntity.ok(GetDayResponse(true,day,"Succesfully added day to user ${user.id}",null ))
        } catch(e: Exception){
            ResponseEntity.ok(GetDayResponse(false, null, "Couldn't Add Day",null))
        }
    }

    fun addFoodToDay(authentication: Authentication, req : AddFootToDayRequest): Day?{
        val day = dayRepo.findDayByDateAndUserId(req.date, authentication.name)

        val food = req.food.toFood()

        day.foodsEaten.add(FoodServing(food.id, req.servings))
        dayRepo.save(day)

        userService.addDayToUser(authentication.name,day.id)

        foodFactsService.saveFood(food)

        return day
    }


    fun deleteFoodFromDay(food: FoodServing, dayId: String){
        val day = dayRepo.findById(dayId)

        day.ifPresent {
            it.foodsEaten.remove(food)
        }
    }

    fun getAllDays(authentication: Authentication): List<Day>{
        return dayRepo.findAllByUserId(authentication.name)
    }

    fun getDay(authentication: Authentication, dateStr: String): ResponseEntity<GetDayResponse>{
        val date: LocalDate = LocalDate.parse(dateStr)

        try {
            val day: Day = dayRepo.findDayByDateAndUserId(date, authentication.name)
            return ResponseEntity.ok(GetDayResponse(true,day,"Found Day",null))
        } catch (e: Exception){
            return ResponseEntity.badRequest().build()
        }
    }
}