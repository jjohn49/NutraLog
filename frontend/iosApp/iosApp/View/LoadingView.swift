//
//  LoadingView.swift
//  iosApp
//
//  Created by John Johnston on 7/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct LoadingView: View {
    
    @State var circles: [AnyView] = [
        AnyView(Circle().foregroundStyle(.green)),
        AnyView(Circle().foregroundStyle(.blue)),
        AnyView(Circle().foregroundStyle(.red)),
        AnyView(Circle().foregroundStyle(.orange))
    ]
    var body: some View {
        HStack{
            ForEach(0..<4){ index in
                circles[index].frame(height: 50)
            }
        }.onAppear(perform:
            rotateCircles
        )
    }
    
    func rotateCircles(){
        
        Task{
            while true{
                try await Task.sleep(nanoseconds: 400000000)
                withAnimation(.linear){
                    let temp = self.circles[0]
                    
                    for x in 0..<3{
                        circles[x] = circles[x+1]
                    }
                    
                    circles[3] = temp
                }
            }
        }
    }
    
}

#Preview {
    LoadingView()
}
