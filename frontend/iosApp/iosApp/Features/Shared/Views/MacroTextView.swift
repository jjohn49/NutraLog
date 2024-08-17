//
//  MacroTextView.swift
//  iosApp
//
//  Created by John Johnston on 8/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct MacroTextView: View {
    
    var text: String
    var macro: Double
    var macroGoal: Double
    let color: Color
    
    var body: some View {
        
        ProgressView(value: macro / macroGoal, label: {
            Text("\(text): \(String(format:"%.1f",macro)) : \(String(format:"%.0f",macroGoal))").bold().foregroundStyle(color).font(.title)
        })
        
    }
}

