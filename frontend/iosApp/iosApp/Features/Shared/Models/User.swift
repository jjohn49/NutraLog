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
    @Published var days: Array<Day> = []
    
    
    func getGoals() -> UserGoal{
        return goals ?? UserGoal(calories: 2000, proteinGrams: 200, carbGrams: 200, fatGrams: 50)
    }
 
}
