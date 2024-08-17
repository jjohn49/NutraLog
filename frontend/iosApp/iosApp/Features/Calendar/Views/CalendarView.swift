//
//  CalendarView.swift
//  iosApp
//
//  Created by John Johnston on 8/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct CalendarView: View {
    @EnvironmentObject var user: User
        @State private var selectDate = Date()
        @State var day: Day? = nil
        @State private var navigate = true

        var dateFormatter: DateFormatter {
            let formatter = DateFormatter()
            formatter.dateFormat = "MMM dd yyyy"
            return formatter
        }
        
        var body: some View {
            NavigationView {
                VStack{
                    
                    DatePicker(
                        "Start Date",
                        selection: $selectDate,
                        displayedComponents: [.date]
                    )
                        .accentColor(Color.blue)
                        .datePickerStyle(.graphical)
                        
                        .onChange(of: selectDate) { newValue in
                            //day = user.getDay(date: selectDate)
                            navigate = true
                        }

                    NavigationLink(isActive: $navigate) {
                        DayView( day: $day, date: $selectDate).navigationTitle(dateFormatter.string(from: selectDate)).onAppear(perform: {
                            day = user.getDay(date: selectDate)
                        })
                    } label: {
                        EmptyView()
                    }
                }
            }
        }
    }


#Preview {
    CalendarView().environmentObject(User())
}
