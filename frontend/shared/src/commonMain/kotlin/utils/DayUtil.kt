package utils

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import models.Day
import requests.AuthenticatedRequest
import response.CreateDayResponse
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

    suspend fun getDaysForUser(req: AuthenticatedRequest): GetAllDaysResponse{
        val uri: String = "http://localhost:8080/day/get/all"
        //println(req)
        val response = client.get(urlString = uri) {
            header(HttpHeaders.Authorization, req.token)
        }

        val ret = GetAllDaysResponse(true,response.body(),"Got All Days",null)
        return ret
    }

    suspend fun CreateDay(req: AuthenticatedRequest): CreateDayResponse{
        val uri: String = "http://localhost:8080/day/create"

        println(req)

        val response = client.post(urlString = uri) {
            header(HttpHeaders.Authorization, req.token)
        }

        val ret = CreateDayResponse(true,response.body(),"Added Day to User",null)
        return ret
    }
}