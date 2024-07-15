//
//  NutrientTrackingView.swift
//  iosApp
//
//  Created by John Johnston on 7/15/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct NutrientTrackingView: View {
    
    @EnvironmentObject var user: User
    
    var body: some View {
        VStack{
            TargetView()
        }
    }
}

#Preview {
    NutrientTrackingView().environmentObject(User())
}
