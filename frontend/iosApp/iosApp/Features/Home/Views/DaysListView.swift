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
    var body: some View {
        ForEach(user.days, id: \.self){day in
            HStack{
                Text(day.id.date)
            }
        }
    }
}

#Preview {
    DaysListView().environmentObject(User())
}
