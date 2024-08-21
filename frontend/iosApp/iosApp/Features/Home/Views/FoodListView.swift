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
        
        VStack(spacing: 20){
            Text("Today's Food").bold().font(.title2)
            ForEach($user.currentDay.foodsEaten, id: \.self){serving in
                FoodRow(foodServing: serving)
            }
        }
    
    }
    
    
}

#Preview {
    
    return FoodListView().environmentObject(User())
}
