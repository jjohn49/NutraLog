package utils

import frontend.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import models.Day
import requests.AddFoodToDayRequest
import requests.AuthenticatedRequest
import requests.CreateDayRequest
import response.AddFoodToDayResponse
import response.GetDayResponse
import response.GetAllDaysResponse

class DayUtil {

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

    suspend fun getDaysForUser(req: AuthenticatedRequest): GetAllDaysResponse{
        val uri: String = "${backend_url}/day/get/all"
        println(req)
        var response: List<Day> = client.get(urlString = uri) {
            header(HttpHeaders.Authorization, req.token)
        }.body()

        println(response)

        response = response.map { d-> Day(d.id,d.userId,d.date,d.foodsEaten,d.toUserNutrients()) }

        val ret = GetAllDaysResponse(true,response,"Got All Days",null)
        return ret
    }

    //Date needs to be in format of yyyy-MM-dd
    suspend fun getDayForUser(auth: AuthenticatedRequest, date: String): GetDayResponse{
        val uri: String = "${backend_url}/day/get?day=${date}"

        val response = client.get(urlString = uri) {
            header(HttpHeaders.Authorization, auth.token)
        }

        return response.body()

    }

    suspend fun CreateDay(auth: AuthenticatedRequest, req: CreateDayRequest): GetDayResponse{
        val uri: String = "${backend_url}/day/create"

        val response = client.post(urlString = uri) {
            header(HttpHeaders.Authorization, auth.token)
            contentType(ContentType.Application.Json)
            setBody(req)
        }

        return response.body()
    }

    suspend fun addFoodToDay(auth: AuthenticatedRequest, req: AddFoodToDayRequest): AddFoodToDayResponse {
        val uri: String = "${backend_url}/day/add/food"

        val response = client.put(urlString = uri){
            header(HttpHeaders.Authorization, auth.token)
            contentType(ContentType.Application.Json)
            setBody(req)
        }

        return response.body()
    }

    companion object{
        //String needs to be formatted: yyyy-MM-dd
        //Should be only used in SwiftUI
        fun createLocalDate(dateStr: String) : LocalDate{
            val split = dateStr.split("-")
            return LocalDate(split.get(0).toInt(), split.get(1).toInt(), split.get(2).toInt())
        }
    }
}