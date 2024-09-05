//
//  User.swift
//  iosApp
//
//  Created by John Johnston on 5/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

@MainActor class User: ObservableObject{
    @Published var username: String = ""
    @Published var token :String = ""
    @Published var goals: UserGoal = UserGoal(calories: 2000, proteinGrams: 200, carbGrams: 200, fatGrams: 100)
    @Published var nutrients: UserNutrients = UserNutrients(calories: 0.0, proteinGrams: 0.0, carbGrams: 0.0, fatGrams: 0.0)
    @Published var days: Array<Day> = []
    @Published var currentDay: Day
    @Published var authenticatedRequest : AuthenticatedRequest = AuthenticatedRequest(token: "")
    
    let dateFormatter = DateFormatter()
    
    let service: Service
    
    init() {
        self.username = ""
        self.token = ""
        self.goals = UserGoal(calories: 2000, proteinGrams: 200, carbGrams: 200, fatGrams: 100)
        self.nutrients = UserNutrients(calories: 0.0, proteinGrams: 0.0, carbGrams: 0.0, fatGrams: 0.0)
        self.days = []
        self.authenticatedRequest = AuthenticatedRequest(token: "")
        self.currentDay = Day(id: "", userId: "", date: .init(year: 1, monthNumber: 1, dayOfMonth: 1), foodsEaten: [], userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0))
        self.dateFormatter.locale = Locale(identifier: "en_US_POSIX")
        self.dateFormatter.dateFormat = "yyyy-MM-dd"
        
        self.service = Service(database: DBBuilder().build())
    }
    
    func getUserFromLocalRepo() async -> LogInResponse{
        do{
            let response: LogInResponse = try await service.getUserFromLocal()
            
            return response
        }catch {
            return LogInResponse(success: false, body: nil, message: "ERROR: Something went wrong when retreiving from the local repo", request: nil)
        }
    }
    
    func addFoodServingToCurrentDay(foodServing: FoodServing) async {
        currentDay.foodsEaten.append(foodServing)
        self.updateUserNutrients()
        
        do{
            try await service.addFoodToDay(authenticatedRequest: authenticatedRequest, addFoodToDayRequest: AddFoodToDayRequest(date: currentDay.date, foodServing: foodServing))
        }catch {
            print("Error sending to the backend")
        }
    }
    
    func deleteFoodServingForCurrentDay(index: Int) async {
        let foodToDelete = currentDay.foodsEaten.remove(at: index)
        self.updateUserNutrients()
        
        do{
            try await service.deleteFoodFromDay(authenticatedRequest: authenticatedRequest, deleteFoodFromDayRequest: DeleteFoodFromDayRequest(dayId: currentDay.id, foodServing: foodToDelete))
        }catch {
            print("Error trying to delete \(foodToDelete.food.name) from current day \(currentDay.date)")
        }
    }
    
    func sortDays(){
        days.sort(by: { d1, d2 in
            dateFormatter.date(from: d1.date.description())! < dateFormatter.date(from: d2.date.description())!
        })
    }
    
    func set(response:LogInResponse) async throws-> Bool{
        if response.success{
            self.username = response.body!.user.email
            self.token = response.body!.token
            self.goals = response.body!.user.userGoals ?? UserGoal(calories: 2000, proteinGrams: 200, carbGrams: 200, fatGrams: 100)
            self.authenticatedRequest = AuthenticatedRequest(token: response.body!.token)
            self.days = response.body!.user.days
            
            sortDays()
            
            if !wasDayCreatedAlready(date: Date.now) {
                await createDayForToday()
            }
            
            currentDay = days.first(where: {d in d.date == self.dateToKotlinDate(date: Date.now)})!
            
            
            self.updateUserNutrients()
            
            try await service.getUserFromBackend(logInResponse: response)
            
            
            return true
        }
        
        return false
    }
    
    func updateUserNutrients(){
        self.nutrients = self.currentDay.toUserNutrients()
    }
    
    
    func refresh() async {
        let r = await pullUser()
        
        if r.success{
            if let user = r.user{
                username = user.id
                goals = user.userGoals ?? UserGoal(calories: 2000, proteinGrams: 200, carbGrams: 200, fatGrams: 100)
                days = user.days
                
                sortDays()
                self.updateUserNutrients()
            }
        }
        
    }
    
    //TODO: Create a function that checkes whether a certain date other than today exists
    //Im thinking just modifying the function below to accept a date param
    
    func wasDayCreatedAlready(date: Date) -> Bool{
        return self.wasDayCreatedAlready(dayStr: self.dateFormatter.string(from: date))
    }
    
    
    //Compares a Kotlin LocalDate to a Swift Date and returns true of they are the same date
    func compareKotlinDateVsSwiftDate(kotlin: Kotlinx_datetimeLocalDate, swift : Date) -> Bool{
        return self.dateFormatter.string(from: swift) == kotlin.description()
    }
    
    
    //Need to use yyyy-MM-dd format
    func wasDayCreatedAlready(dayStr: String) -> Bool{
        return days.contains(where: {day in
            
            return day.date.description() == dayStr
            
            
        } )
    }
    
    func createDayForDate(date: Date) async {
        if !self.wasDayCreatedAlready(date: date) {
            do{
                let response = try await self.addDay(date: date)
                if(response.success){
                    days.append(response.body!)
                }else{
                    print("RESPONSE TO CREATING A DAY FAILED")
                    print(response)
                }
            } catch {
                print("Error adding a day")
            }
        }else{
            print("Day Aslready exists")
        }
    }
    
    //Creates a new Day OBJ for today if it doesn't already exist
    func createDayForToday() async {
        return await createDayForDate(date: Date.now)
    }
    
    func pullUser() async -> UserResponse{
        
        var response: UserResponse = UserResponse(success: false, user: nil, message: "Failed getting response")
        
        do{
            response = try await service.getUser(token: token)
        } catch {
            print("Error getting a response from: pullUser method")
        }
        

        return response
    }
    
    func getAllDays() async throws -> GetAllDaysResponse{
        return try await service.getDaysForUser(authenticatedRequest: authenticatedRequest)
    }
    
    func pullDays() async {
        
        do{
            days = try await getAllDays().body
        }catch {
            print("Error pulling days")
        }
        
    }
    
    func addDay(dateStr: String) async -> GetDayResponse {
        do{
            let response = try await service.createDay(authenticatedRequest: authenticatedRequest, createDayRequest: CreateDayRequest(date: DayUtil.companion.createLocalDate(dateStr: dateStr)))
            
            await self.refresh()
            return response
        } catch {
            print("Error sending add day to backend")
        }
        
        return GetDayResponse(success: false, body: nil, message: "Failed to reach server", request: authenticatedRequest.description())
    }
    
    func addDay(date: Date) async throws -> GetDayResponse{
        return await addDay(dateStr: dateFormatter.string(from: date))
    }
    
    func getDayOnline(date: String) async throws -> GetDayResponse {
        return try await service.getDayForUser(authenticatedRequest: authenticatedRequest, date: date)
    }
    
    func getDayOnline(date: Date) async throws -> GetDayResponse{
        return try await getDayOnline(date: dateFormatter.string(from: date))
    }
    
    func wipeLocalDB() async {
        do{
            try await service.deleteEverythingFromLocalDB()
        }catch {
            print("Couldn't Delete Everything")
        }
    }
    
    func getDay(date: Date) -> Day?{
        return days.first(where: {day in
            return self.compareKotlinDateVsSwiftDate(kotlin: day.date, swift: date)
        })
    }
    
    func dateToKotlinDate(date: Date) -> Kotlinx_datetimeLocalDate{
        return DayUtil.companion.createLocalDate(dateStr: dateFormatter.string(from: date))
    }
    
    func setUserGoal(userGoal:UserGoal) async -> SetUserGoalResponse{
        self.goals = userGoal
        
        do{
            let response = try await service.setUserGoal(authenticatedRequest: authenticatedRequest, setUserGoalsRequest: SetUserGoalsRequest(userGoal: userGoal))
            return response
        }catch {
            print("Error sending User Goal to the backend")
        }
        
        return SetUserGoalResponse(success: false, userGoal: nil, message: "Error Sending UserGoal to the Backend")
    }
    
    
 
}
