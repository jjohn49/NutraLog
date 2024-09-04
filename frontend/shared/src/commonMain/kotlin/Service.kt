import Database.AppDatabase
import Database.DBBuilder
import Database.daos.DayDao
import Database.daos.FoodDao
import Database.daos.UserKMMDao
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

    suspend fun login(logInRequest: LogInRequest): LogInResponse{
        return authUtil.sendLoginRequest(logInRequest)
    }

    suspend fun getUser(token: String): UserResponse{
        return userUtil.getUser(token)
    }

    suspend fun setUserGoal(authenticatedRequest: AuthenticatedRequest, setUserGoalsRequest: SetUserGoalsRequest):SetUserGoalResponse{
        return userUtil.setUserGoal(authenticatedRequest,setUserGoalsRequest)
    }

    suspend fun getFoodBySearch(foodSearchRequest: FoodSearchRequest): FoodSearchResponse{
        return foodUtil.getFoodBySearch(foodSearchRequest)
    }

    suspend fun addFoodToDay(authenticatedRequest: AuthenticatedRequest, addFoodToDayRequest: AddFoodToDayRequest){
        dayUtil.addFoodToDay(authenticatedRequest,addFoodToDayRequest)
    }

    suspend fun getDaysForUser(authenticatedRequest: AuthenticatedRequest): GetAllDaysResponse{
        return dayUtil.getDaysForUser(authenticatedRequest)
    }

    suspend fun getDayForUser(authenticatedRequest: AuthenticatedRequest, date: String): GetDayResponse{
        return dayUtil.getDayForUser(authenticatedRequest,date)
    }

    suspend fun createDay(authenticatedRequest: AuthenticatedRequest, createDayRequest: CreateDayRequest): GetDayResponse{
        return dayUtil.CreateDay(authenticatedRequest,createDayRequest)
    }

    suspend fun deleteFoodFromDay(authenticatedRequest: AuthenticatedRequest, deleteFoodFromDayRequest: DeleteFoodFromDayRequest): DeleteFoodFromDayResponse{
        return dayUtil.deleteFoodFromDay(authenticatedRequest,deleteFoodFromDayRequest)
    }


}