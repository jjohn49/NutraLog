package utils

import frontend.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import requests.FoodSearchRequest
import response.FoodSearchResponse
import response.LogInResponse

class FoodUtil {

    private val backend_url = BuildKonfig.BACKEND_URL

    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getFoodBySearch(req: FoodSearchRequest): FoodSearchResponse{
        val uri : String = "${backend_url}/open-food/get/search/${req.query}"
        val response: FoodSearchResponse = client.get(uri).body()
        return response
    }
}