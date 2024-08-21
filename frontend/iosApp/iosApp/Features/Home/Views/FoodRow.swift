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
    @Binding var foodServing: FoodServing
    @State var userNut: UserNutrients = UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0)
    @EnvironmentObject var user: User
    
    
    var body: some View {
        
        NavigationLink(destination: {
            FoodDetailView(foodServing: $foodServing)
        }, label: {
            HStack{
                
                Text(foodServing.food.name).frame(width: 200, alignment: .leading).fixedSize().lineLimit(1).padding(.leading)
                Spacer()
                TargetView(userNutrients: $userNut, userGoals: $user.goals,width: 75).padding(.trailing)
                
            }
        }).onAppear(perform: {
            userNut = foodServing.toUserNutrients()
        })
    }
}

#Preview {
    @State var foodServing: FoodServing = FoodServing(numberOfServings: 1, food: Food(id: "", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 1, brand: "Store Brand"))
    return FoodRow(foodServing: $foodServing).environmentObject(User())
}
