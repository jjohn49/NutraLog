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
    @Binding var day: Day?
    @Binding var date: Date
    
    init(day: Binding<Day?> = Binding.constant(nil), date: Binding<Date>) {
        self._day = day
        self._date = date
    }
    
    var body: some View {
        if day == nil {
            VStack{
                Text(user.dateFormatter.string(from: date))
                
                Button(action: {
                    Task{
                        
                        try await user.createDayForDate(date: date)
                        day = user.getDay(date: date)
                    }
                }, label: {
                    Text("Create Day")
                })
            }
        }else{
            Text("Day was already created")
        }
    }
    
        
}

#Preview {
    
    @State var date: Date = Date()
    return DayView(date: $date).environmentObject(User())
}
