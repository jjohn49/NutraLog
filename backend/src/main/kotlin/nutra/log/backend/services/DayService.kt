package nutra.log.backend.services

import nutra.log.backend.models.*
import nutra.log.backend.repositories.DayRepository
import nutra.log.backend.requests.AddFoodToDayRequest
import nutra.log.backend.responses.AddFoodToDayResponse
import nutra.log.backend.responses.GetDayResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.Date
import kotlin.Exception

@Service
class DayService(@Autowired val dayRepo: DayRepository) {


    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var foodFactsService: OpenFoodFactsService

    fun addDay(authentication: Authentication, day: Day): ResponseEntity<*>{

        return try{
            if (dayRepo.existsByDateAndUserId(day.date,authentication.name)){
                throw Exception()
            }

            dayRepo.insert(day)
            userService.addDayToUser(authentication, day)
            ResponseEntity.ok(day)
        }catch (e: Exception){
            ResponseEntity.badRequest().body<String>("Failed to create day for date: ${day.date} for user: ${authentication.name}")
        }
    }

    fun addFoodToDay(authentication: Authentication, req : AddFoodToDayRequest): ResponseEntity<FoodServing>{

        return try {
            val day = dayRepo.findDayByDateAndUserId(req.date, authentication.name).orElseThrow()
            day.foodsEaten = day.foodsEaten + req.foodServing
            dayRepo.save(day)
            foodFactsService.saveFood(req.foodServing.food)
            ResponseEntity.ok(req.foodServing)
        }catch (e: NoSuchElementException){
            ResponseEntity.notFound().build()
        }

    }

    fun updateFoodFromDay(oldFood: FoodServing, newFood: FoodServing, dayId: String): ResponseEntity<Day>{
        return try {
            val day = dayRepo.findById(dayId).orElseThrow()
            day.foodsEaten -= oldFood
            day.foodsEaten += newFood
            ResponseEntity.ok(day)
        }catch (e: NoSuchElementException){
            ResponseEntity.notFound().build()
        }
    }

    fun deleteFoodFromDay(authentication: Authentication ,food: FoodServing, dayId: String): ResponseEntity<Day>{

        //getting the day from  repo to make sure it is the correct info
        return try {
            val day: Day = dayRepo.findDayByIdAndUserId(id = dayId, userId = authentication.name).orElseThrow()
            day.foodsEaten -= food
            dayRepo.save(day)
            ResponseEntity.ok(day)
        }catch (exception: NoSuchElementException){
            ResponseEntity.notFound().build()
        }
    }

    fun getAllDays(authentication: Authentication): ResponseEntity<List<Day>>{
        return try {
            val days = dayRepo.findAllByUserId(authentication.name).orElseThrow()
            ResponseEntity.ok(days)
        }catch (e: NoSuchElementException){
            ResponseEntity.notFound().build()
        }

    }

    fun getDay(authentication: Authentication, date: LocalDate): ResponseEntity<Day>{

        return try {
            val day: Day = dayRepo.findDayByDateAndUserId(date, authentication.name).orElseThrow()
            ResponseEntity.ok(day)
        } catch (e: NoSuchElementException){
            ResponseEntity.notFound().build()
        }
    }


}