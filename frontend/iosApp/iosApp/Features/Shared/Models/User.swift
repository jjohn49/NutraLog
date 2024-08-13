//
//  User.swift
//  iosApp
//
//  Created by John Johnston on 5/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

class User: ObservableObject{
    @Published var username: String = ""
    @Published var token :String = ""
    @Published var goals: UserGoal = UserGoal(calories: 2000, proteinGrams: 200, carbGrams: 200, fatGrams: 100)
    @Published var nutrients: UserNutrients = UserNutrients(calories: 0.0, proteinGrams: 0.0, carbGrams: 0.0, fatGrams: 0.0)
    @Published var days: Array<Day> = []
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
        
        dateFormatter.locale = Locale(identifier: "en_US_POSIX")
        dateFormatter.dateFormat = "yyyy-MM-dd"
    }
    
    func set(response:LogInResponse) async throws-> Bool{
        if response.success{
            self.username = response.body!.user.email
            self.token = response.body!.token
            self.goals = response.body!.user.userGoals ?? UserGoal(calories: 2000, proteinGrams: 200, carbGrams: 200, fatGrams: 100)
            self.authenticatedRequest = AuthenticatedRequest(token: response.body!.token)
            
            try await pullDays()
            
            return true
        }
        
        return false
    }
    
    
    func refresh() async throws {
        var r = try await pullUser()
        
        print(r)
        
        if r.success{
            if let user = r.user{
                username = user.id
                goals = user.userGoals ?? UserGoal(calories: 2000, proteinGrams: 200, carbGrams: 200, fatGrams: 100)
            }
        }
        
        try await pullDays()
        
    }
    
    //TODO: Create a function that checkes whether a certain date other than today exists
    //Im thinking just modifying the function below to accept a date param
    
    func checkIfTodayWasCreated() async throws{
        //print(Date.now)
        if !days.contains(where: {day in
            let swift = Date.now
            let kotlin = day.date
            
            return dateFormatter.string(from: swift) == "\(kotlin.year)-\(kotlin.monthNumber < 10 ? "0"+kotlin.monthNumber.formatted() : kotlin.monthNumber.formatted())-\(kotlin.dayOfMonth)"
        } ){
            print("couldn't find date")
            
            do{
                let response = try await self.addDay()
                if(response.success){
                    days.append(response.body!)
                }else{
                    print(response.message)
                }
            } catch {
                print("Error adding a day")
            }
        }else{
            print("Day Aslready exists")
        }
    }
    
    func pullUser() async throws -> UserResponse{
        return try await userUtil.getUser(token: token)
    }
    
    func getAllDays() async throws -> GetAllDaysResponse{
        return try await dayUtil.getDaysForUser(req: authenticatedRequest)
    }
    
    func pullDays() async throws{
        print(try await getAllDays())
        days = try await getAllDays().body
        print(days)
    }
    
    func addDay() async throws -> CreateDayResponse {
        print("ADD DAY")
        do{
            let response = try await dayUtil.CreateDay(req: authenticatedRequest)
            try await self.refresh()
            return response
        } catch {
            print("Error sending add day to backend")
        }
        
        return CreateDayResponse(success: false, body: nil, message: "Failed to reach server", request: authenticatedRequest.description())
    }
    
    
 
}
