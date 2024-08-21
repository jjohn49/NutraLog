//
//  FoodDetailView.swift
//  iosApp
//
//  Created by John Johnston on 8/20/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct FoodDetailView: View {
    
    @EnvironmentObject var user: User
    @Binding var foodServing: FoodServing
    @State var nutrients: UserNutrients = UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0)
    
    var body: some View {
        
        VStack{
            TargetView(userNutrients: $user.nutrients, userGoals: $user.goals)
            Text("Calories: \(String(format: "%.1f", nutrients.calories))g")
            Text("Protein: \(String(format: "%.1f", nutrients.proteinGrams))g")
            Text("Carbs: \(String(format: "%.1f", nutrients.carbGrams))g")
            Text("Fat: \(String(format: "%.1f", nutrients.fatGrams))g")
            
            Stepper("Servings: \(String(format: "%.1f", foodServing.numberOfServings))", value: $foodServing.numberOfServings,step: 0.5)
                
            Button(action: {
                Task{
                    try await user.addFoodServingToCurrentDay(foodServing: foodServing)
                }
            }, label: {
                Text("Add Food")
            })
        }.onAppear(perform: {
            nutrients = foodServing.toUserNutrients()
        })
    }
}



#Preview {
    @State var s: FoodServing = FoodServing(numberOfServings: 1, food: Food(id: "", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 2, brand: "Store Brand"))
    @State var user: User = User()
    return FoodDetailView(foodServing: $s).environmentObject(user)
}
