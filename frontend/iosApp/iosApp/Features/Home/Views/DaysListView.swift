//
//  DaysListView.swift
//  iosApp
//
//  Created by John Johnston on 7/15/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct DaysListView: View {
    @EnvironmentObject var user: User
    @StateObject var viewModel: DayListViewModel = DayListViewModel()
    var body: some View {
        if(user.days.isEmpty){
            Text("You have no days.")
            Button("Add Day", action: {
                Task{
                    try await viewModel.addNewDay(action: user.addDay)
                }
            })
        }else{
            ForEach(user.days, id: \.self){day in
                HStack{
                    Text(day.id.date)
                }
            }
        }
    }
}

#Preview {
    DaysListView().environmentObject(User())
}
