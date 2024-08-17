//
//  MacroView.swift
//  iosApp
//
//  Created by John Johnston on 8/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct MacroView: View {
    @Binding var userNut: UserNutrients
    @Binding var userGoals: UserGoal
    var body: some View {
        
            VStack{
                MacroTextView(text: "Calories", macro: userNut.calories, macroGoal: Double(userGoals.calories), color: .red).padding()
                
                MacroTextView(text: "Protein", macro: userNut.proteinGrams, macroGoal: Double(userGoals.proteinGrams), color: .blue).padding()
                
                MacroTextView(text: "Carbs", macro: userNut.carbGrams, macroGoal: Double(userGoals.carbGrams), color: .green).padding()
                
                MacroTextView(text: "Fat", macro: userNut.fatGrams, macroGoal: Double(userGoals.fatGrams), color: .yellow).padding()
                
            }
        }
        
    }


#Preview {
    @State var nut: UserNutrients = UserNutrients(calories: 100,proteinGrams: 0,carbGrams: 0,fatGrams: 0)
    @State var goal: UserGoal = UserGoal(calories: 2000,proteinGrams: 200,carbGrams: 200,fatGrams: 100)
    return MacroView(userNut: $nut, userGoals: $goal)
}
