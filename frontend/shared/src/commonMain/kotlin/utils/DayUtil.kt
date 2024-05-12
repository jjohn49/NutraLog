package utils

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import models.Day
import response.GetAllDaysResponse
import response.LogInResponse

class DayUtil {

    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getDaysForUser(token : String): GetAllDaysResponse{
        val uri: String = "http://localhost:8080/day/get/all"
        //println(req)
        val response = client.get(urlString = uri) {
            header("Authorization", token)
        }

        val ret = GetAllDaysResponse(true,response.body(),"Got All Days",null)
        return ret
    }
}