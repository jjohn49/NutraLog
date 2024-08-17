//
//  HomePageView.swift
//  iosApp
//
//  Created by John Johnston on 7/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared


struct HomePageView: View {
    
    @EnvironmentObject var user: User
    @State var loading: Bool = false
    @State var day: Day = Day(id: DayId(timestamp: 0, date: ""), userId: "", date: .init(year: 2024, monthNumber: 1, dayOfMonth: 1), foodsEaten: [])
    
    var body: some View {
        
        if(user.token == "" && user.username == ""){
            if(loading){
                LoadingView()
            }else{
                LoginView(loading: $loading, day: $day).environmentObject(self.user)
            }
        }else{
            TabView{
                NutrientTrackingView(day: $day).tabItem {
                    Text("Home")
                }
                CalendarView().tabItem {
                    Text("Calendar")
                }
                
            }
        }
    }
}

#Preview {
    return HomePageView().environmentObject(User())
}
