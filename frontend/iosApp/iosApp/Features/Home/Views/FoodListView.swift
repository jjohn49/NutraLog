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
    @Binding var day: Day
    
    var body: some View {
        
        VStack(spacing: 20){
            Text("Today's Food").bold().font(.title2)
            ForEach($day.foodsEaten, id: \.self){serving in
                FoodRow(foodServing: serving)
            }
        }
    
    }
    
    
}

#Preview {
    @State var d = Day(id: DayId(timestamp: 0, date: ""), userId: "", date: .init(year: 2024, monthNumber: 1, dayOfMonth: 1), foodsEaten: [
        FoodServing(numberOfServings: 0.1, food: Food(id: "", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 2, brand: "Stop & shop")),
        FoodServing(numberOfServings: 0.1, food: Food(id: "", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 2, brand: "Stop & shop"))
    ], userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0)
    )
    return FoodListView(day: $d).environmentObject(User())
}
