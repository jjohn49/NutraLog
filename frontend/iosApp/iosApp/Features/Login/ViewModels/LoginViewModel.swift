//
//  LoginViewModel.swift
//  iosApp
//
//  Created by John Johnston on 7/15/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

class LoginViewModel: ObservableObject {
    @Published var username:String = ""
    @Published var password: String = ""
    @Published var failedLogin: Bool = false
    
    //TODO: Set this up
    @Published var keepSignedIn: Bool = false
    
    
    let reqUtil: RequestUtil = RequestUtil()
    
    @Published var response: LogInResponse?
    
    func login() async throws {
        //print("Sending to Backend")
        response = try await reqUtil.sendLoginRequest(req: LogInRequest(username: username, password: password))
    }
    
    
    
    
}
