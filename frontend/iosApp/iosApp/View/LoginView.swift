//
//  LoginView.swift
//  iosApp
//
//  Created by John Johnston on 5/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

//import SwiftUI
//import Shared
//
//struct LoginView: View {
//    var body: some View {
//        
//        @State var viewModel: LogInRequest = LogInRequest(username: "", password: "")
//        
//        VStack {
//                    
//                    Spacer()
//                    
//                    VStack {
//                        TextField(
//                            "Login.UsernameField.Title",
//                            text: $viewModel.username
//                        )
//                        .autocapitalization(.none)
//                        .disableAutocorrection(true)
//                        .padding(.top, 20)
//                        
//                        Divider()
//                        
//                        SecureField(
//                            "Login.PasswordField.Title".localized,
//                            text: $viewModel.password
//                        )
//                        .padding(.top, 20)
//                        
//                        Divider()
//                    }
//                    
//                    Spacer()
//                    
//                    Button(
//                        action: viewModel.login,
//                        label: {
//                            Text("Login.LoginButton.Title".localized)
//                                .font(.system(size: 24, weight: .bold, design: .default))
//                                .frame(maxWidth: .infinity, maxHeight: 60)
//                                .foregroundColor(Color.white)
//                                .background(Color.blue)
//                                .cornerRadius(10)
//                        }
//                    )
//                }
//                .padding(30)
//    }
//}
//
//#Preview {
//    LoginView()
//}
