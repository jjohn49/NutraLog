package nutra.log.backend.controllers

import nutra.log.backend.models.Day
import nutra.log.backend.repositories.DayRepository
import nutra.log.backend.services.DayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("day")
class DayController(@Autowired val repo: DayRepository, @Autowired val service: DayService) {

    @PostMapping("add")
    fun addDay(@RequestBody day: Day): Day?{
        service.addDay(day)
        return day
    }
}