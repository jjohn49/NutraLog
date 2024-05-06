package nutra.log.backend.controllers

import nutra.log.backend.models.Day
import nutra.log.backend.models.Food
import nutra.log.backend.repositories.DayRepository
import nutra.log.backend.requests.DayRequest
import nutra.log.backend.requests.AddFootToDayRequest
import nutra.log.backend.services.DayService
import nutra.log.backend.services.UserService
import org.apache.tomcat.util.http.parser.Authorization
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("day")
class DayController(@Autowired val repo: DayRepository, @Autowired val service: DayService) {

    @PostMapping("add")
    fun addDay(@RequestBody req: DayRequest): Day?{
        val newDay = Day(req.userId)
        service.addDay(newDay)
        return newDay
    }

    @PutMapping("add/food")
    fun addFood(@RequestBody req: AddFootToDayRequest): Food{
        service.addFoodToDay(req)
        return req.food.toFood()
    }

    @PutMapping("add/food/today")
    fun addFoodToday(authentication: Authentication, @RequestBody req:AddFootToDayRequest): Food{

    }

    @PostMapping("get/all")
    fun getAllDays(@RequestBody req: DayRequest): List<Day>{
        return service.getAllDays(req)
    }


}