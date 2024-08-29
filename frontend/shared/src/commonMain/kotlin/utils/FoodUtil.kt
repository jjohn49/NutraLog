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
import models.FoodSearch
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

        val query = req.query.replace(' ','+')

        val uri : String = "${backend_url}/open-food/get/search/${query}"
        val response = client.get(uri)

        return if(response.status.value in 200..299){
            FoodSearchResponse(true,response.body(),"Got Food Searches for query: ${req.query}",null)
        }else{
            FoodSearchResponse(false,null,"Could not get Food Search for query: ${req.query}",null)
        }


    }
}