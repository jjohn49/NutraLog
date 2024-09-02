//
//  CalendarView.swift
//  iosApp
//
//  Created by John Johnston on 8/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import MijickCalendarView

struct CalendarView: View {
    @EnvironmentObject var user: User
    @State private var selectDate: Date? = Date()
    @State var day: Day? = nil
    @State private var navigate = false
    @State var startDate: Date = Date.now

    var dateFormatter: DateFormatter {
        let formatter = DateFormatter()
        formatter.dateFormat = "MMM dd yyyy"
        return formatter
    }
    
    var body: some View {
        NavigationView {
            VStack{
                MCalendarView(selectedDate: $selectDate, selectedRange: nil){ config in
                    
                    config
                    .daysHorizontalSpacing(8)
                    .daysVerticalSpacing(20)
                    .monthsBottomPadding(20)
                    .monthsTopPadding(40)
                    .startMonth(startDate)
                    .endMonth(Date.now)
                    .dayView { currentDate, isCurrentMonth, selectedDate, selectedRange -> СustomDayView in
                        return СustomDayView(date: currentDate, day: user.getDay(date: currentDate), isCurrentMonth: isCurrentMonth, selectedDate: selectedDate, selectedRange: selectedRange)
                    }
                        
                }.padding(.horizontal)
                .onChange(of: selectDate!) {newValue in
                    navigate = true
                }
                .onAppear(perform: {
                    startDate = user.dateFormatter.date(from: user.days[0].date.description())!
                })
            
                
                NavigationLink(isActive: $navigate) {
                    DayDetailView(date: $selectDate).navigationTitle(dateFormatter.string(from: selectDate!)).onAppear(perform: {
                        day = user.getDay(date: selectDate!)
                    }).navigationTitle(selectDate!.description).navigationBarTitleDisplayMode(.large)
                } label: {
                    EmptyView()
                }
            }.padding(.horizontal)
        }
    }
}







#Preview {
    
    @State var user: User = User()
    
    var food: Food = Food(id: "", name: "Chicken", servingSize: "1 Breast", calories: 100, proteinGrams: 20, carbGrams: 1, fatGrams: 1, brand: "Store")
    
    var foods: [FoodServing] = [
        FoodServing(numberOfServings: 1, food: food),
        FoodServing(numberOfServings: 1, food: food),
        FoodServing(numberOfServings: 1, food: food),
        FoodServing(numberOfServings: 1, food: food)
    ]
    
    user.days = [
        Day(id: "", userId: user.username, date: .init(year: 2024, monthNumber: 8, dayOfMonth: 30), foodsEaten: foods, userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0)),
        Day(id: "", userId: user.username, date: .init(year: 2024, monthNumber: 8, dayOfMonth: 29), foodsEaten: foods, userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0)),
        Day(id: "", userId: user.username, date: .init(year: 2024, monthNumber: 8, dayOfMonth: 28), foodsEaten: foods, userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0))
    ]
    
    
    
    return CalendarView().environmentObject(user)
}
