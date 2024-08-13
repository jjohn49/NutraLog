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
                    Text("\(selectDate, formatter: dateFormatter)")
                        .foregroundColor(.black)
                        .font(.system(size: 30))
                        .fontWeight(.bold)
                    
                    DatePicker(
                        "Start Date",
                        selection: $selectDate,
                        displayedComponents: [.date]
                    )
                        .accentColor(Color.blue)
                        .datePickerStyle(.graphical)
                        
                        .onChange(of: selectDate) { newValue in
                            navigate = true
                        }

                    NavigationLink(isActive: $navigate) {
                        DayView(date: $selectDate)
                    } label: {
                        EmptyView()
                    }
                }
            }
        }
    }


#Preview {
    CalendarView()
}
