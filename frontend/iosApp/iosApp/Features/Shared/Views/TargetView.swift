//
//  TargetView.swift
//  iosApp
//
//  Created by John Johnston on 7/15/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct TargetView: View {
    
    @Binding var userNutrients: UserNutrients
    @Binding var userGoals: UserGoal
    
    var width: CGFloat = 100
    
    var body: some View {
        
        ZStack{
            ProgressView(value: userNutrients.calories / Double(userGoals.calories)).progressViewStyle(TargetProgressViewStyle(lineWidth: width * 0.1, color: .red)).frame(width: width * 0.9)
            
            ProgressView(value: userNutrients.proteinGrams / Double(userGoals.proteinGrams)).progressViewStyle(TargetProgressViewStyle(lineWidth: width * 0.1, color: .blue)).frame(width: width * 0.7)
            
            ProgressView(value: userNutrients.carbGrams / Double(userGoals.carbGrams)).progressViewStyle(TargetProgressViewStyle(lineWidth: width * 0.1, color: .green)).frame(width: width * 0.5)
            
            ProgressView(value: userNutrients.fatGrams / Double(userGoals.fatGrams)).progressViewStyle(TargetProgressViewStyle(lineWidth: width * 0.1, color: .yellow)).frame(width: width * 0.3)
            
        }
        
        
    }
}

#Preview {
    @State var nuts: UserNutrients = UserNutrients(calories: 500,proteinGrams: 0,carbGrams: 0,fatGrams: 0)
    @State var goals: UserGoal = UserGoal(calories: 2000,proteinGrams: 200,carbGrams: 200,fatGrams: 100)
    return TargetView(userNutrients: $nuts,userGoals: $goals)
}
