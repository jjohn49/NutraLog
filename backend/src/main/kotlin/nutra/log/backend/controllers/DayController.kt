package nutra.log.backend.controllers

import nutra.log.backend.models.Day
import nutra.log.backend.repositories.DayRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("day")
class DayController(@Autowired val repo: DayRepository) {

    @PostMapping("add")
    fun addDay(@RequestBody day: Day): Day?{
        try{
            repo.insert(day)
            return day
        }catch (exception : Exception){
            return null
        }
    }
}