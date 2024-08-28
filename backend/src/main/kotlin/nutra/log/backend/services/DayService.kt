package nutra.log.backend.services

import nutra.log.backend.models.*
import nutra.log.backend.repositories.DayRepository
import nutra.log.backend.requests.AddFoodToDayRequest
import nutra.log.backend.responses.AddFoodToDayResponse
import nutra.log.backend.responses.GetDayResponse

import org.springframework.beans.factory.annotation.Autowired
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
            if (!dayRepo.findById(day.id.toString()).isPresent) {
                dayRepo.insert(day)
                userService.addDayToUser(authentication, day)
                ResponseEntity.ok(GetDayResponse(true,day,"Succesfully added day to user ${authentication.name}",null ))
            }else{
                ResponseEntity.ok(GetDayResponse(true,day,"Day Already Exists",null))
            }



        } catch(e: Exception){
            ResponseEntity.ok(GetDayResponse(false, null, "Couldn't Add Day",null))
        }
    }

    fun addFoodToDay(authentication: Authentication, req : AddFoodToDayRequest): ResponseEntity<AddFoodToDayResponse>{
        val day = dayRepo.findDayByDateAndUserId(req.date, authentication.name)

        day.foodsEaten = day.foodsEaten + req.foodServing
        dayRepo.save(day)


        foodFactsService.saveFood(req.foodServing.food)

        return ResponseEntity.ok(AddFoodToDayResponse(true,req.foodServing,"Added Food to Day ${req.date.toString()}",null))
    }


//    fun deleteFoodFromDay(food: FoodServing, dayId: String){
//        val day = dayRepo.findById(dayId)
//
//        day.ifPresent {
//            it.foodsEaten -= food
//        }
//    }

    fun getAllDays(authentication: Authentication): List<Day>{
        val days = dayRepo.findAllByUserId(authentication.name)
        return days
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

//    fun dayToDayKMM(day: Day): DayKMM{
//        val foodServings: ArrayList<FoodServingKMM> = arrayListOf()
//        day.foodsEaten.forEach {serving ->
//            foodServings.add(FoodServingKMM(foodFactsService.getOpenFoodFactByCode(serving.foodId.toString()).toFood(),serving.numberOfServings))
//        }
//
//        return DayKMM(day.id.toString(),day.userId,day.date,foodServings)
//    }
}