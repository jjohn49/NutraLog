package nutra.log.backend.controllers

import nutra.log.backend.models.Day
import nutra.log.backend.models.Food
import nutra.log.backend.requests.AddFootToDayRequest
import nutra.log.backend.services.DayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("day")
class DayController(@Autowired val service: DayService) {

    @PostMapping("create")
    fun createDay(authentication: Authentication): Day?{
        val newDay = Day(userId = authentication.name)
        service.addDay(authentication, newDay)
        return newDay
    }

    @PutMapping("add/food")
    fun addFood(authentication: Authentication, @RequestBody req: AddFootToDayRequest): Food{
        service.addFoodToDay(authentication, req)
        return req.food.toFood()
    }


    @GetMapping("get/all")
    fun getAllDays(authentication: Authentication): List<Day>{
        return service.getAllDays(authentication)
    }


}