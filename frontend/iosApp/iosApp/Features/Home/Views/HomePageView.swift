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
    
    var body: some View {
        
        if(user.token == "" && user.username == ""){
            if(loading){
                LoadingView()
            }else{
                LoginView(loading: $loading).environmentObject(self.user)
            }
        }else{
            VStack{
                NutrientTrackingView()
                DaysListView()
            }
        }
    }
}

#Preview {
    return HomePageView().environmentObject(User(username: "JJ", token: "token"))
}
