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
    @State var showAddCustomFood: Bool = false
    @State var showAddFoodBySearch: Bool = false
    
    var body: some View {
        
        if #available(iOS 16.0, *) {
            
            NavigationStack{
                ScrollView{
                    MacroView().padding()
                    FoodListView()
                }.navigationTitle("Today").toolbar{
                    ToolbarItem{
                        Menu(content: {
                            Button(action: {
                                showAddCustomFood = true
                            }, label: {
                                Text("Add Custom Food")
                            })
                            
                            Button(action: {
                                showAddFoodBySearch = true
                            }, label: {
                                Text("Add Food By Search")
                            })
                        }, label: {
                            Text("Menu")
                        })
                    }
                }.refreshable {
                    Task{
                        try await user.currentDay = user.getDayOnline(date: Date.now).body!
                        user.updateUserNutrients()
                        print(user.nutrients)
                    }
                }
            }.sheet(isPresented: $showAddCustomFood, content: {
                AddCustomFoodView()
            }).sheet(isPresented: $showAddFoodBySearch, content: {
                AddFoodBySearchView()
            })
        }else {
            // Fallback on earlier versions
            //Update ya damn phone
            Text("Womp Womp")
        }
    }
}

struct AddCustomFoodView: View {
    @State var calories: Double = 0.0
    @State var protein: Double = 0.0
    @State var carbs: Double = 0.0
    @State var fat: Double = 0.0
    
    @State var food: Food = Food(id: "", name: "", servingSize: "", calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0, brand: "")
    
    let formatter: NumberFormatter = {
            let formatter = NumberFormatter()
            formatter.numberStyle = .decimal
            return formatter
        }()
    
    var body: some View {
        VStack{
            Text("Add Custom Food").font(.title).bold()
            
            TextField("Calories", value: $calories, formatter: formatter)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                            .padding()
            TextField("Protein", value: $protein, formatter: formatter)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                            .padding()
            TextField("Carbs", value: $carbs, formatter: formatter)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                            .padding()
            TextField("Fat", value: $fat, formatter: formatter)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                            .padding()
            
            Button(action: {
                //submit
            }, label: {
                Text("Submit")
            })
        }
    }
}

#Preview {
    @State var d = Day(id: DayId(timestamp: 0, date: ""), userId: "", date: .init(year: 2024, monthNumber: 1, dayOfMonth: 1), foodsEaten: [
        FoodServing(numberOfServings: 1, food: Food(id: "1", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 2, brand: "Stop & shop")),
        FoodServing(numberOfServings: 1, food: Food(id: "", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 2, brand: "Stop & shop"))
    ], userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0)
    )
    
    
    
    return NutrientTrackingView().environmentObject(User())
    
    
}
