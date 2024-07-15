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
    
    let dayUtil: DayUtil = DayUtil()
    let userUtil: UserUtil = UserUtil()
    
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
        return try await dayUtil.getDaysForUser(token: token)
    }
    
    func pullDays() async throws{
        print(try await getAllDays())
        days = try await getAllDays().body
        print(days)
    }
 
}
