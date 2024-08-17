//
//  FoodRow.swift
//  iosApp
//
//  Created by John Johnston on 8/17/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct FoodRow: View {
    var foodServing: FoodServing
    @State var userNut: UserNutrients = UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0)
    @EnvironmentObject var user: User
    
    
    var body: some View {
        
        HStack(spacing: 50){
            Text(foodServing.food.name)
            Text("\(foodServing.numberOfServings.formatted(.number)) x \(foodServing.food.servingSize)")
            TargetView(userNutrients: $userNut, userGoals: $user.goals, width: 75)
            
        }.onAppear(perform: {
            userNut = foodServing.toUserNutrients()
        })
        
    }
}

#Preview {
    var foodServing: FoodServing = FoodServing(numberOfServings: 1, food: Food(id: "", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 1, brand: "Store Brand"))
    return FoodRow(foodServing: foodServing).environmentObject(User())
}
