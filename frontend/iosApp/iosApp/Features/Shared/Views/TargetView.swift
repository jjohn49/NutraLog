//
//  TargetView.swift
//  iosApp
//
//  Created by John Johnston on 7/15/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TargetView: View {
    
    @ObservedObject var viewModel: TargetViewModel = TargetViewModel()
    
    @EnvironmentObject var user: User
    
    var body: some View {
        GeometryReader{ geo in
            ZStack{
                ProgressView(value: viewModel.getPercentage(numerator: viewModel.calories, divider: user.getGoals().calories)).progressViewStyle(TargetProgressViewStyle(lineWidth: 30, color: .red)).frame(width: geo.size.width * 0.9)
                
                ProgressView(value: viewModel.getPercentage(numerator: viewModel.proteinGrams, divider: user.getGoals().proteinGrams)).progressViewStyle(TargetProgressViewStyle(lineWidth: 30, color: .blue)).frame(width: geo.size.width * 0.7)
                
                ProgressView(value: viewModel.getPercentage(numerator: viewModel.carbGrams, divider: user.getGoals().carbGrams)).progressViewStyle(TargetProgressViewStyle(lineWidth: 30, color: .green)).frame(width: geo.size.width * 0.5)
                
                ProgressView(value: viewModel.getPercentage(numerator: viewModel.fatGrams, divider: user.getGoals().fatGrams)).progressViewStyle(TargetProgressViewStyle(lineWidth: 30, color: .yellow)).frame(width: geo.size.width * 0.3)
                
            }.frame(width: geo.size.width)
        }
        
    }
}

#Preview {
    TargetView().environmentObject(User())
}
