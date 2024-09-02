//
//  CustomDayView.swift
//  iosApp
//
//  Created by John Johnston on 9/2/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Shared
import MijickCalendarView

struct СustomDayView: DayView {
    let date: Date
    let day: Day?
    let isCurrentMonth: Bool
    let selectedDate: Binding<Date?>?
    let selectedRange: Binding<MDateRange?>?
    @EnvironmentObject var user: User
    
}

extension СustomDayView {
    func createDayLabel() -> AnyView {
        ZStack {
            createBackgroundView()
            createDayLabelText()
        }
        .erased() // cast to AnyView
    }
    
    func createSelectionView() -> AnyView {
        Circle()
            .fill(isSelected() ? .blue : .clear)
            .transition(.asymmetric(insertion: .scale(scale: 0.5).combined(with: .opacity), removal: .opacity))
            .erased()
    }
 }

private extension СustomDayView {
   func createBackgroundView() -> some View {
       
       ZStack{
           if day != nil{
               TargetView(userNutrients: Binding.constant(UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0)), userGoals: $user.goals,width: 50)
           }
       }
       
    }

  func createDayLabelText() -> some View {
        Text(getStringFromDay(format: "d"))
            .font(.system(size: 17))
            .foregroundColor(.black).bold()
    }
}
