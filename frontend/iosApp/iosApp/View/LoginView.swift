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
    
    @State var failedLogin: Bool = false
    
    var body: some View {
        
        VStack {
            
            if failedLogin {
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
                    Task{
                        try await viewModel.login()

                        if let response = viewModel.response{
                            if(response.success){
                                user.token = response.body.token
                                let userResponse = try await viewModel.getUser(token: user.token)
                                
                                print(userResponse)
                                
                                user.username = userResponse.user!.id
                                user.goals = userResponse.user!.userGoals
                                user.days = userResponse.user!.days as! Array<Day>
                                
                            }else{
                                failedLogin = true
                            }
                        }
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
    
    let reqUtil: RequestUtil = RequestUtil()
    let userUtil: UserUtil = UserUtil()
    
    @Published var response: LogInResponse?
    
    func login() async throws {
        //print("Sending to Backend")
        response = try await reqUtil.sendLoginRequest(req: LogInRequest(username: username, password: password))
    }
    
    func getUser(token: String) async throws -> UserResponse{
        return try await userUtil.getUser(token: token)
    }
    
}


