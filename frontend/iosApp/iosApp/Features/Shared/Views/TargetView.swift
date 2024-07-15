//
//  TargetView.swift
//  iosApp
//
//  Created by John Johnston on 7/15/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TargetView: View {
    
    @EnvironmentObject var user: User
    
    var body: some View {
        GeometryReader{ geo in
            ZStack{
                ProgressView().progressViewStyle(TargetProgressViewStyle(lineWidth: 30, color: .red)).frame(width: geo.size.width * 0.9)
                
                ProgressView().progressViewStyle(TargetProgressViewStyle(lineWidth: 30, color: .blue)).frame(width: geo.size.width * 0.7)
                
                ProgressView().progressViewStyle(TargetProgressViewStyle(lineWidth: 30, color: .green)).frame(width: geo.size.width * 0.5)
                
                ProgressView().progressViewStyle(TargetProgressViewStyle(lineWidth: 30, color: .yellow)).frame(width: geo.size.width * 0.3)
                
            }.frame(width: geo.size.width)
        }
        
    }
}

#Preview {
    TargetView().environmentObject(User())
}
