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
        //loading = true
        Task{
            try await viewModel.login()
            
            if let response = viewModel.response{
                if(response.success){
                    user.token = response.body.token
                    user.authenticatedRequest = AuthenticatedRequest(token: user.token)
                    try await user.refresh()
                    loading = false
                    
                }else{
                    viewModel.failedLogin = true
                    loading = false
                }
            }
        }
    }
    
    
}


#Preview {
    @State var b = false
    
    return LoginView(loading: $b).environmentObject(User())
}


