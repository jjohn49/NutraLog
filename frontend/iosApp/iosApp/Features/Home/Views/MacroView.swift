//
//  MacroView.swift
//  iosApp
//
//  Created by John Johnston on 8/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct MacroView: View {
    @EnvironmentObject var user: User
    var body: some View {
        GeometryReader{ geo in
            VStack{
                MacroTextView(text: "Calories", macro: Float(user.nutrients.calories), macroGoal: Int(user.goals.calories), color: .red).padding()
                
                MacroTextView(text: "Protein", macro: Float(user.nutrients.proteinGrams), macroGoal: Int(user.goals.proteinGrams), color: .blue).padding()
                
                MacroTextView(text: "Carbs", macro: Float(user.nutrients.carbGrams), macroGoal: Int(user.goals.carbGrams), color: .green).padding()
                
                MacroTextView(text: "Fat", macro: Float(user.nutrients.fatGrams), macroGoal: Int(user.goals.fatGrams), color: .yellow).padding()

            }.frame(width: geo.size.width)
        }
        
    }
}

#Preview {
    MacroView().environmentObject(User())
}
