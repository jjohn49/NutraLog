//
//  DayView.swift
//  iosApp
//
//  Created by John Johnston on 8/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct DayView: View {
    
    @EnvironmentObject var user: User
    
    @Binding var date: Date
    @State var day: Day = Day(id: DayId(timestamp: 0, date: ""), userId: "", foodsEaten: [])
    
    var body: some View {
        if day.id.date == ""{
            VStack{
                Text(user.dateFormatter.string(from: date))
                
                Button(action: {
                    Task{
                        try await user.checkIfTodayWasCreated()
                    }
                }, label: {
                    Text("Create Day")
                })
            }.onAppear(perform: {
                if user.days.contains(where: {if day}){
                    
                }
            })
        }
    }
    
        
}

#Preview {
    
    @State var date: Date = Date()
    return DayView(date: $date).environmentObject(User())
}
