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
    
    func login() async -> LogInResponse{
        var response: LogInResponse = LogInResponse(success: false, body: nil, message: "Failed to login", request: nil)
        
        do{
            response =  try await reqUtil.sendLoginRequest(req: LogInRequest(username: username, password: password))
        } catch {
            print(response.message)
        }
        
        return response
    }
    
    
    
    
}
