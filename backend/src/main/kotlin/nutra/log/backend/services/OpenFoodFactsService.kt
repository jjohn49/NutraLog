package nutra.log.backend.services

import kotlinx.serialization.json.Json
import nutra.log.backend.models.Food
import nutra.log.backend.models.FoodSearch
import nutra.log.backend.models.OpenFoodFact
import nutra.log.backend.models.OpenFoodFactSearch
import nutra.log.backend.repositories.FoodRepository
import nutra.log.backend.responses.FoodSearchResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class OpenFoodFactsService {

    @Autowired
    private lateinit var foodRepository: FoodRepository

    val json = Json { ignoreUnknownKeys = true }
    fun sendRequestTo(uri: String): HttpResponse<String> {
        val client = HttpClient.newBuilder().build()

        val request = HttpRequest.newBuilder().uri(URI(uri)).build()

        return client.send(request, HttpResponse.BodyHandlers.ofString())
    }
    fun getOpenFoodFactByCode(id: String):OpenFoodFact{
        val uri = "https://world.openfoodfacts.net/api/v2/product/${id}?fields=product_name,nutriments"
        val response = sendRequestTo(uri)
        val foodFacts = json.decodeFromString<OpenFoodFact>(response.body())

        return foodFacts
    }

    fun getFoodByCode(id: String): Food{
        val food: Food = foodRepository.findById(id).orElseGet {
            getOpenFoodFactByCode(id).toFood()
        }
        return food
    }

    fun getFoodBySearch(query: String):ResponseEntity<FoodSearchResponse>{

        val uri = "https://world.openfoodfacts.org/cgi/search.pl?search_terms=${query}&search_simple=1&action=process&json=1"
        val response = sendRequestTo(uri)
        val foodFacts = json.decodeFromString<OpenFoodFactSearch>(response.body())

        val foods: FoodSearch = foodFacts.toFoodSearch()

        return ResponseEntity.ok(FoodSearchResponse(true, foodFacts.toFoodSearch(), "Got Search Results", null))
    }

    fun saveFood(food: Food){
        if(!foodRepository.existsById(food.id.toString())){
            foodRepository.insert(food)
        }
    }

    fun getFoodFromRepo(foodId: String) = foodRepository.findById(foodId)
}