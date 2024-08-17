//
//  NutrientTrackingView.swift
//  iosApp
//
//  Created by John Johnston on 7/15/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared



struct NutrientTrackingView: View {
    
    @EnvironmentObject var user: User
    @Binding var day: Day
    @State var userNut = UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0)
    
    var body: some View {
       
        if #available(iOS 16.0, *) {
            ZStack{
                GeometryReader{ geo in
                    NavigationStack{
                        ScrollView{
                            MacroView(userNut: $userNut, userGoals: $user.goals).padding()
                            FoodListView(day: $day)
                        }.navigationTitle("Today")
                    }.onAppear(perform: {
                        userNut = day.toUserNutrients()
                    })
                    
                    Button(action: {
                        print("Clicked")
                    }, label: {
                        ZStack{
                            Circle().stroke(lineWidth: 5)
                            Image(systemName: "plus")
                        }
                    }).position(x:geo.size.width/2,y:geo.size.height - 50).frame(width: 75, height: 75)
                }
            }
        } else {
            // Fallback on earlier versions
            Text("Womp Womp")
        }
    }
    
}

#Preview {
    @State var d = Day(id: DayId(timestamp: 0, date: ""), userId: "", date: .init(year: 2024, monthNumber: 1, dayOfMonth: 1), foodsEaten: [
        FoodServing(numberOfServings: 1, food: Food(id: "", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 2, brand: "Stop & shop")),
        FoodServing(numberOfServings: 1, food: Food(id: "", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 2, brand: "Stop & shop"))
    ]
    )
    
    
    
    return NutrientTrackingView(day: $d).environmentObject(User())
    
    
}
