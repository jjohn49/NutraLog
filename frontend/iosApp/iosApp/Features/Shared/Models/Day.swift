//
//  Day.swift
//  iosApp
//
//  Created by John Johnston on 5/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

class Day {
    var id: String
    var userId: String
    var date: Date
    
    
    init(id: String, userId: String, date: Date) {
        self.id = id
        self.userId = userId
        self.date = date
    }
}
