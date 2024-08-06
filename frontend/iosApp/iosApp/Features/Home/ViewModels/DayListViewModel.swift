//
//  DayListViewModel.swift
//  iosApp
//
//  Created by John Johnston on 8/4/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared


class DayListViewModel: ObservableObject{
    
    
    func addNewDay(action: () async throws -> CreateDayResponse) async throws{
        let response = try await action()
        print(response)
    }
}
