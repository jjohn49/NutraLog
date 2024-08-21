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
    @EnvironmentObject var user:User
    
    var body: some View {
        
            VStack{
                MacroTextView(text: "Calories", macro: $user.nutrients.calories, macroGoal: $user.goals.calories, color: .red).padding()
                
                MacroTextView(text: "Protein", macro: $user.nutrients.proteinGrams, macroGoal: $user.goals.proteinGrams, color: .blue).padding()
                
                MacroTextView(text: "Carbs", macro: $user.nutrients.carbGrams, macroGoal: $user.goals.carbGrams, color: .green).padding()
                
                MacroTextView(text: "Fat", macro: $user.nutrients.fatGrams, macroGoal: $user.goals.fatGrams, color: .yellow).padding()
                
            }
        }
    }


#Preview {
    return MacroView().environmentObject(User())
}
