//
//  SettingsView.swift
//  iosApp
//
//  Created by John Johnston on 9/2/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct SettingsView: View {
    @EnvironmentObject var user: User
    
    @State var calories: Int = 2000
    @State var protein: Int = 175
    @State var carbs: Int = 225
    @State var fat: Int = 50
    var body: some View {
        List{
            //TODO: Add Sliders for Goals
            //Maybe do some type of form/menu as well
            Section(content: {
                Stepper("Calories: \(String(format: "%d", calories))g", value: $calories, onEditingChanged: {_ in
                    distributeCaloriesToMacros()
                })
                
                Stepper("Protein: \(String(format: "%d", protein))g", value: $protein, onEditingChanged: {_ in
                    calculateTotalCalories()
                })
                
                Stepper("Carbs: \(String(format: "%d", carbs))g", value: $carbs, onEditingChanged: {_ in
                    calculateTotalCalories()
                })
                
                Stepper("Fat: \(String(format: "%d", fat))g", value: $fat, onEditingChanged: {_ in
                    calculateTotalCalories()
                })
                
                Button("Set User Goals", action: {
                    //Call something on the backend to set the UserGoals
                    Task{
                        let userGoal = UserGoal(calories: Int32(calories), proteinGrams: Int32(protein), carbGrams: Int32(carbs), fatGrams: Int32(fat))
                        let rep = await user.setUserGoal(userGoal: userGoal)
                        //print(rep)
                    }
                })
            }, header: {
                Text("User Goals")
            })
            
            Button("Sign Out", action: {
                //TODO: Create a Sign out function
            }).foregroundStyle(.red)
        }.onAppear(perform: {
            calories = Int(user.goals.calories)
            protein = Int(user.goals.proteinGrams)
            carbs = Int(user.goals.carbGrams)
            fat = Int(user.goals.fatGrams)
        })
    }
    
    func calculateTotalCalories(){
        calories = 4 * protein + 4 * carbs + 8 * fat
    }
    
    func distributeCaloriesToMacros(){
        protein = calories * 7 / 20 / 4
        carbs = calories * 9 / 20 / 4
        fat = calories * 2 / 5 / 8
    }
}

#Preview {
    SettingsView()
}
