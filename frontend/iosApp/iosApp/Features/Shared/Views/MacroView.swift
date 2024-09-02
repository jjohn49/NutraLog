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
    
    
    @Binding var nutrients: UserNutrients
    @Binding var goals: UserGoal
    
    var body: some View {
        
            VStack{
                MacroTextView(text: "Calories", macro: $nutrients.calories, macroGoal: $goals.calories, color: .red).padding()
                
                MacroTextView(text: "Protein", macro: $nutrients.proteinGrams, macroGoal: $goals.proteinGrams, color: .blue).padding()
                
                MacroTextView(text: "Carbs", macro: $nutrients.carbGrams, macroGoal: $goals.carbGrams, color: .green).padding()
                
                MacroTextView(text: "Fat", macro: $nutrients.fatGrams, macroGoal: $goals.fatGrams, color: .yellow).padding()
                
            }
        }
    }


#Preview {
    @State var user = User()
    
    return MacroView(nutrients: $user.nutrients, goals: $user.goals)
}
