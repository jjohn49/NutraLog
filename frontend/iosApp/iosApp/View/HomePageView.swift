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
    
    var body: some View {
        
        if(user.token == ""){
            LoginView().environmentObject(self.user)
        }else{
            Text("Logged In")
        }
    }
}

#Preview {
    HomePageView()
}
