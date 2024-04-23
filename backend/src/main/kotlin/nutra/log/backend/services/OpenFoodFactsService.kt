package nutra.log.backend.services

import kotlinx.serialization.json.Json
import nutra.log.backend.models.OpenFoodFact
import nutra.log.backend.models.OpenFoodFactSearch
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class OpenFoodFactsService {

    val json = Json { ignoreUnknownKeys = true }
    fun sendRequestTo(uri: String): HttpResponse<String> {
        val client = HttpClient.newBuilder().build()

        val request = HttpRequest.newBuilder().uri(URI(uri)).build()

        return client.send(request, HttpResponse.BodyHandlers.ofString())
    }
    fun getFoodByCode(id: String):OpenFoodFact{
        val uri = "https://world.openfoodfacts.net/api/v2/product/${id}?fields=product_name,nutriments"

        val response = sendRequestTo(uri)

        println(response.body())
        val foodFacts = json.decodeFromString<OpenFoodFact>(response.body())

        return foodFacts
    }

    fun getFoodBySearch(query: String):OpenFoodFactSearch{

        val uri = "https://world.openfoodfacts.org/cgi/search.pl?search_terms=${query}&search_simple=1&action=process&json=1"
        val response = sendRequestTo(uri)

        println(response.body())
        val foodFacts = json.decodeFromString<OpenFoodFactSearch>(response.body())

        return foodFacts
    }
}