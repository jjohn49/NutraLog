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
    var macro: Float
    var macroGoal: Int
    let color: Color
    
    var body: some View {
        
        ProgressView(value: Float(Int(macro) / macroGoal), label: {
            Text("\(text): \(String(format:"%.1f",macro)) : \(macroGoal)").bold().foregroundStyle(color).font(.title)
        })
        
    }
}

