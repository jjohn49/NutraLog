package nutra.log.backend.controllers

import nutra.log.backend.models.OpenFoodFact
import nutra.log.backend.services.OpenFoodFactsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


@RestController
@RequestMapping("open-food")
class OpenFoodFactsController(@Autowired val openFoodFactsService: OpenFoodFactsService) {

    @GetMapping("get/food/{code}")
    fun getFoodByCode(@PathVariable("code") code:String):OpenFoodFact{
        return openFoodFactsService.getFoodByCode(code)
    }

    @GetMapping("get/search/{query}")
    fun getFoodBySearch(@PathVariable("query") query:String):Any{
        return openFoodFactsService.getFoodBySearch(query)
    }
}