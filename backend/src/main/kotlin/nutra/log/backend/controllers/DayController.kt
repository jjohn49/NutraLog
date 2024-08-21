package nutra.log.backend.controllers

import nutra.log.backend.models.Day
import nutra.log.backend.models.DayKMM
import nutra.log.backend.models.Food
import nutra.log.backend.requests.AddFoodToDayRequest
import nutra.log.backend.requests.CreateDayRequest
import nutra.log.backend.responses.AddFoodToDayResponse
import nutra.log.backend.responses.GetDayResponse
import nutra.log.backend.services.DayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("day")
class DayController(@Autowired val service: DayService) {

    @PostMapping("create")
    fun createDay(authentication: Authentication, @RequestBody createDayRequest: CreateDayRequest): ResponseEntity<GetDayResponse>{
        val newDay = Day(userId = authentication.name, date = createDayRequest.date)
        return service.addDay(authentication, newDay)
    }

    @PutMapping("add/food")
    fun addFood(authentication: Authentication, @RequestBody req: AddFoodToDayRequest): ResponseEntity<AddFoodToDayResponse>{
        return service.addFoodToDay(authentication, req)

    }


    @GetMapping("get/all")
    fun getAllDays(authentication: Authentication): List<DayKMM>{
        val ret = service.getAllDays(authentication)
        println(ret)
        return ret
    }

    @GetMapping("get")
    fun getDay(@RequestParam day: String, authentication: Authentication): ResponseEntity<GetDayResponse>{
        return service.getDay(authentication, day)
    }


}