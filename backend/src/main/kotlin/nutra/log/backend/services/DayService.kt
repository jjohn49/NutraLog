package nutra.log.backend.services

import nutra.log.backend.models.*
import nutra.log.backend.repositories.DayRepository
import nutra.log.backend.repositories.FoodRepository
import nutra.log.backend.repositories.UserRepository
import nutra.log.backend.requests.AddFootToDayRequest
import nutra.log.backend.requests.DayRequest
import org.bson.types.ObjectId

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class DayService(@Autowired val dayRepo: DayRepository, @Autowired val userRepo: UserRepository, @Autowired val openFoodFactsService: OpenFoodFactsService, @Autowired val foodRepository: FoodRepository) {

    @Autowired
    private lateinit var userService: UserService

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
    fun getFoodByCode(code: String): OpenFoodFact{
        return openFoodFactsService.getFoodByCode(code)
    }
    fun getFoodBySearch(food: String): OpenFoodFactSearch{
        return openFoodFactsService.getFoodBySearch(food)
    }
    fun addFoodToDay(authentication: Authentication, req : AddFootToDayRequest): Day?{
        val day = dayRepo.findDayByDateAndId(req.date, ObjectId(authentication.name))

        val food = req.food.toFood()
        foodRepository.insert(food)
        day.foodsEaten.add(FoodServing(food.id, req.servings))
        dayRepo.save(day)

        userService.addDayToUser(authentication.name,day.id)

        return day


    }
    fun getAllFoodServings(day: Day): List<Pair<Double,Food>>{
        val ret = arrayListOf<Pair<Double,Food>>()
        for(i in day.foodsEaten){
            val temp = foodRepository.findById(i.foodId.toString())

            temp.ifPresent {
                ret.add(Pair(i.numberOfServings,it))
            }
        }

        return ret
    }

    fun deleteFoodFromDay(food: FoodServing, dayId: String){
        val day = dayRepo.findById(dayId)

        day.ifPresent {
            it.foodsEaten.remove(food)
        }
    }

    fun getAllDays(req: DayRequest): List<Day>{
        return dayRepo.findAllByUserId(ObjectId(req.userId))
    }
}