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
    
    @ObservedObject var viewModel: FoodDetailViewModel
    
    init(viewModel: FoodDetailViewModel) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        
        VStack{
            TargetView(userNutrients: $viewModel.nutrients, userGoals: $viewModel.goals)
            Text("Calories: \(String(format: "%.1f", viewModel.nutrients.calories))g")
            Text("Protein: \(String(format: "%.1f", viewModel.nutrients.proteinGrams))g")
            Text("Carbs: \(String(format: "%.1f", viewModel.nutrients.carbGrams))g")
            Text("Fat: \(String(format: "%.1f", viewModel.nutrients.fatGrams))g")
            
            Stepper("Servings: \(String(format: "%.1f", viewModel.numOfServigs))", value: $viewModel.numOfServigs,step: 0.5)
                .onChange(of: viewModel.numOfServigs){ _ in
                    viewModel.updateNumOfServings()
                }
            Button(action: {
                Task{
                    try await viewModel.addFoodToToday(foodServing:viewModel.serving)
                }
            }, label: {
                Text("Add Food")
            })
        }
    }
}

class FoodDetailViewModel: ObservableObject{
    var user: User
    @Published var nutrients: UserNutrients
    @Published var numOfServigs: Double
    @Binding var goals: UserGoal
    @Binding var serving: FoodServing
    @Binding var day: Day
    
    let util: DayUtil = DayUtil()
    
    init(user: User, numOfServings: Double = 1.0, goals: Binding<UserGoal>, foodServing: Binding<FoodServing>, day: Binding<Day>) {
        self.user = user
        self.nutrients = foodServing.wrappedValue.toUserNutrients()
        self.numOfServigs = numOfServings
        self._goals = goals
        self._serving = foodServing
        self._day = day
    }
    
    func addFoodToToday(foodServing: FoodServing) async throws -> FoodServing?{
        let response = try await util.addFoodToDay(auth: user.authenticatedRequest, req: AddFoodToDayRequest(date: user.dateToKotlinDate(date: Date.now), foodServing: foodServing))
        
        day.addFoodServingToDay(foodServing: foodServing)
        
        return response.body
    }
    
    func updateNumOfServings(){
        serving = FoodServing(numberOfServings: numOfServigs, food: serving.food)
        nutrients = serving.toUserNutrients()
    }
    
}

#Preview {
    @State var s: FoodServing = FoodServing(numberOfServings: 1, food: Food(id: "", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 2, brand: "Store Brand"))
    @State var user: User = User()
    return FoodDetailView(viewModel: FoodDetailViewModel(user: user,goals: $user.goals,foodServing: $s, day:$user.currentDay))
}
