package utils

import Database.daos.DayDao
import frontend.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
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
import requests.DeleteFoodFromDayRequest
import response.AddFoodToDayResponse
import response.DeleteFoodFromDayResponse
import response.GetDayResponse
import response.GetAllDaysResponse

class DayUtil {

    constructor(dayDao: DayDao){
        this.dao = dayDao
    }

    val dao: DayDao

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
        var response: HttpResponse = client.get(urlString = uri) {
            header(HttpHeaders.Authorization, req.token)
        }

        return if (response.status.value in 200..299) {
            var body:List<Day> = response.body<List<Day>>()

            body = body.map { d->
                d.userNutrients = d.toUserNutrients()
                d }

            GetAllDaysResponse(true,body,"Got day",null)
        }else{
            GetAllDaysResponse(false, listOf(),"Failed to get day",null)
        }



//        response = response.map { d-> Day(d.id,d.userId,d.date,d.foodsEaten,d.toUserNutrients()) }
//
//        val ret = GetAllDaysResponse(true,response,"Got All Days",null)
//        return ret
    }

    //Date needs to be in format of yyyy-MM-dd
    suspend fun getDayForUser(auth: AuthenticatedRequest, date: String): GetDayResponse{
        val uri: String = "${backend_url}/day/get?day=${date}"

        val response:HttpResponse = client.get(urlString = uri) {
            header(HttpHeaders.Authorization, auth.token)
        }

        return if (response.status.value in 200..299) {
            GetDayResponse(true,response.body(),"Got day",null)
        }else{
            GetDayResponse(false,null,"Failed to get day",null)
        }

    }

    suspend fun CreateDay(auth: AuthenticatedRequest, req: CreateDayRequest): GetDayResponse{
        val uri: String = "${backend_url}/day/create"

        val response = client.post(urlString = uri) {
            header(HttpHeaders.Authorization, auth.token)
            contentType(ContentType.Application.Json)
            setBody(req)
        }

        return if (response.status.value in 200..299) {
            GetDayResponse(true,response.body(),"Got day",null)
        }else{
            GetDayResponse(false,null,"Failed to get day.  Failed with exit code ${response.status}. \nFull Response: \n\n\n${response}",null)
        }
    }

    suspend fun addFoodToDay(auth: AuthenticatedRequest, req: AddFoodToDayRequest): AddFoodToDayResponse {
        val uri: String = "${backend_url}/day/add/food"

        val response = client.put(urlString = uri){
            header(HttpHeaders.Authorization, auth.token)
            contentType(ContentType.Application.Json)
            setBody(req)
        }

        return if (response.status.value in 200..299) {
            AddFoodToDayResponse(true,response.body(),"Got day",null)
        }else{
            AddFoodToDayResponse(false,null,"Failed to get day",null)
        }

    }

    suspend fun deleteFoodFromDay(auth: AuthenticatedRequest, req: DeleteFoodFromDayRequest): DeleteFoodFromDayResponse{
        val uri: String = "${backend_url}/day/delete/food"

        val response = client.delete(uri){
            header(HttpHeaders.Authorization, auth.token)
            contentType(ContentType.Application.Json)
            setBody(req)
        }

        return if (response.status.value in 200..299) {
            DeleteFoodFromDayResponse(true,response.body(),"Deleted Serving from Day",null)
        }else{
            println(response)
            DeleteFoodFromDayResponse(false,null,"Failed Delete serving from day",null)
        }
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