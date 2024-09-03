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
    @State var day: Day = Day(id: "", userId: "", date: .init(year: 2024, monthNumber: 1, dayOfMonth: 1), foodsEaten: [],userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0))
    
    var body: some View {
        
        if(user.token == "" && user.username == ""){
            if(loading){
                LoadingView()
            }else{
                LoginView(loading: $loading, day: $day).environmentObject(self.user)
            }
        }else{
            TabView{
                NutrientTrackingView().environmentObject(self.user).tabItem {
                    Text("Home")
                }
                CalendarView().tabItem {
                    Text("Calendar")
                }
                SettingsView().tabItem {
                    Text("Settings")
                }
                
            }
        }
    }
}

#Preview {
    return HomePageView().environmentObject(User())
}
