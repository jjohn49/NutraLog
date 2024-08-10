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
