//
//  LoginView.swift
//  iosApp
//
//  Created by John Johnston on 5/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct LoginView: View {
    
    @EnvironmentObject var user: User
    
    @ObservedObject var viewModel: LoginViewModel = LoginViewModel()
    
    @Binding var loading: Bool
    
    @Binding var day: Day
    
    
    var body: some View {
        
        VStack {
            
            if viewModel.failedLogin {
                Text("Incorrect Username or Password.  try Again").bold().background(.red)
            }
            
            Spacer()
            
            VStack {
                TextField(
                    "user.username",
                    text: $viewModel.username
                )
                .autocapitalization(.none)
                .disableAutocorrection(true)
                .padding(.top, 20)
                
                Divider()
                
                SecureField(
                    "Login.PasswordField.Title",
                    text: $viewModel.password
                )
                .padding(.top, 20)
                
                Divider()
                
            }
            
            Spacer()
            
            Button(
                action: {
                    logInButtonAction()
                },
                label: {
                    Text("Login.LoginButton.Title")
                        .font(.system(size: 24, weight: .bold, design: .default))
                        .frame(maxWidth: .infinity, maxHeight: 60)
                        .foregroundColor(Color.white)
                        .background(Color.blue)
                        .cornerRadius(10)
                }
            )
        }
        .padding(30)
    }
    
    func logInButtonAction() {
        loading = true
        Task{
            let response = try await viewModel.login()
            print(response)
            if(try await user.set(response: response)){
                if(user.getDay(date: Date.now) == nil){
                    try await user.createDayForToday()
                }
                day = user.getDay(date: Date.now)!
                user.currentDay = day
                loading = false
            }else{
                viewModel.failedLogin = true
                loading = false
            }
            
        }
    }
    
    
}


#Preview {
    @State var b = false
    @State var d = Day(id: DayId(timestamp: 0, date: ""), userId: "", date: .init(year: 2024, monthNumber: 1, dayOfMonth: 1), foodsEaten: [], userNutrients: UserNutrients(calories: 0, proteinGrams: 0, carbGrams: 0, fatGrams: 0))
    
    return LoginView(loading: $b, day: $d).environmentObject(User())
}


