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
    @Published var goals: UserGoal? = nil
    @Published var nutrients: UserNutrients? = nil
    @Published var days: Array<Day> = []
    
    var authenticatedRequest : AuthenticatedRequest
    
    let dayUtil: DayUtil = DayUtil()
    let userUtil: UserUtil = UserUtil()
    
    init(username: String = "", token: String = "", goals: UserGoal? = nil, nutrients: UserNutrients? = nil, days: Array<Day> = []) {
        self.username = username
        self.token = token
        self.goals = goals
        self.nutrients = nutrients
        self.days = days
        self.authenticatedRequest = AuthenticatedRequest(token: "")
    }
    
    func refresh() async throws {
        var r = try await pullUser()
        
        print(r)
        
        if r.success{
            if let user = r.user{
                username = user.id
                goals = user.userGoals
            }
        }
        
        try await pullDays()
        
    }
    
    func pullUser() async throws -> UserResponse{
        return try await userUtil.getUser(token: token)
    }
    
    func getGoals() -> UserGoal{
        return goals ?? UserGoal(calories: 2000, proteinGrams: 200, carbGrams: 200, fatGrams: 50)
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
