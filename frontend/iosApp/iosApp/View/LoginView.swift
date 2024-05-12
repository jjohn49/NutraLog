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
    var body: some View {
        
        @ObservedObject var viewModel: LoginViewModel = LoginViewModel()
        
    
        VStack {
            
            Spacer()
            
            VStack {
                TextField(
                    "Login.UsernameField.Title",
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
                    Task{
                        try await viewModel.login()
                    }
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
}

class LoginViewModel: ObservableObject {
    @Published var username:String = ""
    @Published var password: String = ""
    
    let util: RequestUtil = RequestUtil()
    
    @Published var response: LogInResponse?
    
    func login() async throws {
        response = try await util.sendLoginRequest(req: LogInRequest(username: username, password: password))
    }
    
}


