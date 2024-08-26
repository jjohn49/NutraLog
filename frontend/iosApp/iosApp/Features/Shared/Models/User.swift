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
    
    let dayUtil: DayUtil = DayUtil()
    let userUtil: UserUtil = UserUtil()
    
    let dateFormatter = DateFormatter()
    
    init() {
        self.username = ""
        self.token = ""
        self.goals = UserGoal(calories: 2000, proteinGrams: 200, carbGrams: 200, fatGrams: 100)
        self.nutrients = UserNutrients(calories: 0.0, proteinGrams: 0.0, carbGrams: 0.0, fatGrams: 0.0)
        self.days = []
        self.authenticatedRequest = AuthenticatedRequest(token: "")
        self.currentDay = Day(id: DayId(timestamp: 0,date: ""), userId: "", date: .init(year: 1, monthNumber: 1, dayOfMonth: 1), foodsEaten: [], userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0))
        dateFormatter.locale = Locale(identifier: "en_US_POSIX")
        dateFormatter.dateFormat = "yyyy-MM-dd"
    }
    
    func addFoodServingToCurrentDay(foodServing: FoodServing) async {
        currentDay.foodsEaten.append(foodServing)
        self.updateUserNutrients()
        
        do{
            try await dayUtil.addFoodToDay(auth: authenticatedRequest, req: AddFoodToDayRequest(date: currentDay.date, foodServing: foodServing))
        }catch {
            print("Error sending to the backend")
        }
    }
    
    func set(response:LogInResponse) async throws-> Bool{
        if response.success{
            self.username = response.body!.user.email
            self.token = response.body!.token
            self.goals = response.body!.user.userGoals ?? UserGoal(calories: 2000, proteinGrams: 200, carbGrams: 200, fatGrams: 100)
            self.authenticatedRequest = AuthenticatedRequest(token: response.body!.token)
            
            
            do{
                await pullDays()
                currentDay = days.first(where: {d in d.date == self.dateToKotlinDate(date: Date.now)})!
            }catch {
                print("Error pulliong days in set method")
            }
            
            self.updateUserNutrients()
            
            return true
        }
        
        return false
    }
    
    func updateUserNutrients(){
        self.nutrients = self.currentDay.toUserNutrients()
    }
    
    
    func refresh() async {
        var r = await pullUser()
        
        if r.success{
            if let user = r.user{
                username = user.id
                goals = user.userGoals ?? UserGoal(calories: 2000, proteinGrams: 200, carbGrams: 200, fatGrams: 100)
            }
        }
        
        await pullDays()
        
    }
    
    //TODO: Create a function that checkes whether a certain date other than today exists
    //Im thinking just modifying the function below to accept a date param
    
    func wasDayCreatedAlready(date: Date) -> Bool{
        return self.wasDayCreatedAlready(dayStr: self.dateFormatter.string(from: date))
    }
    
    
    //Compares a Kotlin LocalDate to a Swift Date and returns true of they are the same date
    func compareKotlinDateVsSwiftDate(kotlin: Kotlinx_datetimeLocalDate, swift : Date) -> Bool{
        return self.dateFormatter.string(from: swift) == "\(kotlin.year)-\(kotlin.monthNumber < 10 ? "0"+kotlin.monthNumber.formatted() : kotlin.monthNumber.formatted())-\(kotlin.dayOfMonth)"
    }
    
    
    //Need to use yyyy-MM-dd format
    func wasDayCreatedAlready(dayStr: String) -> Bool{
        return days.contains(where: {day in
            let kotlin = day.date
            
            return dayStr == "\(kotlin.year)-\(kotlin.monthNumber < 10 ? "0"+kotlin.monthNumber.formatted() : kotlin.monthNumber.formatted())-\(kotlin.dayOfMonth)"
        } )
    }
    
    func createDayForDate(date: Date) async throws{
        if !self.wasDayCreatedAlready(date: date) {
            
            do{
                let response = try await self.addDay(date: date)
                if(response.success){
                    days.append(response.body!)
                }else{
                    print("RESPONSE TO CREATING A DAY FAILED")
                    print(response.message)
                }
            } catch {
                print("Error adding a day")
            }
        }else{
            print("Day Aslready exists")
        }
    }
    
    //Creates a new Day OBJ for today if it doesn't already exist
    func createDayForToday() async throws{
        return try await createDayForDate(date: Date.now)
    }
    
    func pullUser() async -> UserResponse{
        
        var response: UserResponse = UserResponse(success: false, user: nil, message: "Failed getting response")
        
        do{
            response = try await userUtil.getUser(token: token)
        } catch {
            print("Error getting a response from: pullUser method")
        }
        
        return response
    }
    
    func getAllDays() async throws -> GetAllDaysResponse{
        return try await dayUtil.getDaysForUser(req: authenticatedRequest)
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
            let response = try await dayUtil.CreateDay(auth: authenticatedRequest, req: CreateDayRequest(date: DayUtil.companion.createLocalDate(dateStr: dateStr)))
            
            try await self.refresh()
            return response
        } catch {
            print("Error sending add day to backend")
        }
        
        return GetDayResponse(success: false, body: nil, message: "Failed to reach server", request: authenticatedRequest.description())
    }
    
    func addDay(date: Date) async throws -> GetDayResponse{
        print(dateFormatter.string(from: date))
        return await addDay(dateStr: dateFormatter.string(from: date))
    }
    
    func getDayOnline(date: String) async throws -> GetDayResponse {
        return try await dayUtil.getDayForUser(auth: authenticatedRequest, date: date)
    }
    
    func getDayOnline(date: Date) async throws -> GetDayResponse{
        return try await getDayOnline(date: dateFormatter.string(from: date))
    }
    
    func getDay(date: Date) -> Day?{
        return days.first(where: {day in
            return self.compareKotlinDateVsSwiftDate(kotlin: day.date, swift: date)
        })
    }
    
    func dateToKotlinDate(date: Date) -> Kotlinx_datetimeLocalDate{
        return DayUtil.companion.createLocalDate(dateStr: dateFormatter.string(from: date))
    }
    
    
 
}
