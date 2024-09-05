import Database.AppDatabase
import Database.DBBuilder
import Database.daos.DayDao
import Database.daos.FoodDao
import Database.daos.UserKMMDao
import models.Day
import models.Food
import models.UserKMM
import requests.AddFoodToDayRequest
import requests.AuthenticatedRequest
import requests.CreateDayRequest
import requests.DeleteFoodFromDayRequest
import requests.FoodSearchRequest
import requests.LogInRequest
import requests.SetUserGoalsRequest
import response.AddFoodToDayResponse
import response.DeleteFoodFromDayResponse
import response.FoodSearchResponse
import response.GetAllDaysResponse
import response.GetDayResponse
import response.LogInBody
import response.LogInResponse
import response.SetUserGoalResponse
import response.UserResponse
import utils.AuthUtil
import utils.DayUtil
import utils.FoodUtil
import utils.UserUtil

class Service(

    val database: AppDatabase,
    val authUtil: AuthUtil,
    val dayUtil: DayUtil,
    val userUtil: UserUtil,
    val foodUtil: FoodUtil,
    val userKMMDao: UserKMMDao,
    val dayDao: DayDao,
    val foodDao: FoodDao
) {
    constructor(database: AppDatabase) : this(database,
        AuthUtil(),
        DayUtil(),
        UserUtil(),
        FoodUtil(),
        database.getUserDao(),
        database.getDayDao(),
        database.getFoodDao()
    )

    suspend fun getUserFromBackend(logInResponse: LogInResponse){
        logInResponse.body?.let { lb ->

            if(userKMMDao.numOfUsersWithId(lb.user.id) > 0){
                userKMMDao.update(lb.user)
            }else {
                userKMMDao.insert(lb.user)
            }

            val days: List<Day> = lb.user.days
            days.map { d ->

                if(dayDao.numOfDaysWithId(d.id)> 0){
                    dayDao.update(d)
                }else {
                    dayDao.insert(d)
                }
                d.foodsEaten.map { f ->

                    if(foodDao.numOfFoodsWithId(f.food.id) == 0){
                        foodDao.insert(f.food)
                    }

                }
            }
        }
    }

    suspend fun getUserFromLocal(): LogInResponse{
        val userAndDays = userKMMDao.getUserAndDays()

        if(userAndDays.count() > 0){
            val user = userAndDays.first().userKMM
            user.days = userAndDays.first().days

            return LogInResponse(true, LogInBody("",user),"Received User from local DB",null)
        }

        return LogInResponse(false,null,"No User in Local DB", null)
    }

    suspend fun login(logInRequest: LogInRequest): LogInResponse{
        return authUtil.sendLoginRequest(logInRequest)
    }

    suspend fun getUser(token: String): UserResponse{
        return userUtil.getUser(token)
    }

    suspend fun setUserGoal(authenticatedRequest: AuthenticatedRequest, setUserGoalsRequest: SetUserGoalsRequest):SetUserGoalResponse{

        //There should only be 1 user at a time
        val user: UserKMM = userKMMDao.getUserAndDays().first().userKMM
        user.userGoals = setUserGoalsRequest.userGoal
        userKMMDao.update(user)

        return userUtil.setUserGoal(authenticatedRequest,setUserGoalsRequest)
    }

    suspend fun getFoodBySearch(foodSearchRequest: FoodSearchRequest): FoodSearchResponse{
        return foodUtil.getFoodBySearch(foodSearchRequest)
    }

    suspend fun deleteEverythingFromLocalDB(){
        userKMMDao.deleteFuckingEverything()
        dayDao.deleteFuckingEverything()
        foodDao.deleteFuckingEverything()
    }

    suspend fun addFoodToDay(authenticatedRequest: AuthenticatedRequest, addFoodToDayRequest: AddFoodToDayRequest): AddFoodToDayResponse{

        val day: Day = dayDao.getDayForDate(addFoodToDayRequest.date.toString())

        day.foodsEaten += addFoodToDayRequest.foodServing

        dayDao.update(day)

        return dayUtil.addFoodToDay(authenticatedRequest,addFoodToDayRequest)
    }

    suspend fun getDaysForUser(authenticatedRequest: AuthenticatedRequest): GetAllDaysResponse{
        return dayUtil.getDaysForUser(authenticatedRequest)
    }

    suspend fun getDayForUser(authenticatedRequest: AuthenticatedRequest, date: String): GetDayResponse{
        return dayUtil.getDayForUser(authenticatedRequest,date)
    }

    suspend fun createDay(authenticatedRequest: AuthenticatedRequest, createDayRequest: CreateDayRequest): GetDayResponse{

        val response: GetDayResponse = dayUtil.CreateDay(authenticatedRequest,createDayRequest)

        if (response.success) {
            response.body?.let { dayDao.insert(it) }
        }else{
            val user = userKMMDao.getUserAndDays().first().userKMM
            dayDao.insert(Day("",user.id,createDayRequest.date))
        }

        return response
    }

    suspend fun deleteFoodFromDay(authenticatedRequest: AuthenticatedRequest, deleteFoodFromDayRequest: DeleteFoodFromDayRequest): DeleteFoodFromDayResponse{
        return dayUtil.deleteFoodFromDay(authenticatedRequest,deleteFoodFromDayRequest)
    }


}