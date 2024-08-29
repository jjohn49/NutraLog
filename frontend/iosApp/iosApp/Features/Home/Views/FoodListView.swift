//
//  FoodListView.swift
//  iosApp
//
//  Created by John Johnston on 8/17/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct FoodListView: View {
    
    @EnvironmentObject var user: User
    
    var body: some View {
        
        LazyVStack{
            Text("Today's Food").bold().font(.title2)
            
            
            ForEach(Array($user.currentDay.foodsEaten.enumerated()), id: \.offset){index, serving in
                FoodRow(index: index, foodServing: serving, showFoodDetailAddButton: false).onDelete {
                    Task{
                        await user.deleteFoodServingForCurrentDay(index:index)
                    }
                }
            }
            
            
        }
    
    }
    
    
}

#Preview {
    
    var user: User = User()
    
    var food: Food = Food(id: "Chicken", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 1, brand: "Store")
    
    var foodsEaten: [FoodServing] = [
        FoodServing(numberOfServings: 1, food: food),
        FoodServing(numberOfServings: 1, food: food),
        FoodServing(numberOfServings: 1, food: food),
        FoodServing(numberOfServings: 1, food: food)
    ]
    
    user.currentDay = Day(id: "", userId: "", date: user.dateToKotlinDate(date: Date.now), foodsEaten: foodsEaten, userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0))
    
    return FoodListView().environmentObject(user)
}
