//
//  DayView.swift
//  iosApp
//
//  Created by John Johnston on 8/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct DayDetailView: View {
    
    @EnvironmentObject var user: User
    //@Binding var day: Day?
    @Binding var date: Date?
    
    @State var day: Day
    
    init(date: Binding<Date?>) {
        self._date = date
        self.day = Day(id: "", userId: "", date: .init(year: 2024,monthNumber: 1, dayOfMonth: 1), foodsEaten: [], userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0))
        
    }
    
    
    
    var body: some View {
        if day.id == "" {
            VStack{
                Button(action: {
                    Task{
                        
                        await user.createDayForDate(date: date!)
                        
                        day = user.getDay(date: date!) ?? Day(id: "", userId: "", date: .init(year: 2024,monthNumber: 1, dayOfMonth: 1), foodsEaten: [], userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0))
                    }
                }, label: {
                    Text("Create Day")
                })
            }.onAppear(perform: {
                if let temp = user.getDay(date: date!){
                    day = temp
                    day.userNutrients = day.toUserNutrients()
                }
            })
        }else{
            ScrollView{
                MacroView(nutrients: $day.userNutrients, goals: $user.goals).padding(.horizontal)
                FoodListView(day: $day, showSwipeToDelete: false).padding(.horizontal)
            }
        }
        
    }
        
}

#Preview {
    
    @State var date: Date? = Date()
    @State var d: Day = Day(id: "", userId: "", date: .init(year: 2024, monthNumber: 1, dayOfMonth: 1), foodsEaten: [
        FoodServing(numberOfServings: 1, food: Food(id: "1", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 2, brand: "Stop & shop")),
        FoodServing(numberOfServings: 1, food: Food(id: "", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 2, brand: "Stop & shop"))
    ], userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0)
    )
    
    @State var user: User = User()
    user.currentDay = d
    return DayDetailView(date: $date).environmentObject(user)
}
