//
//  AddFoodBySearchView.swift
//  iosApp
//
//  Created by John Johnston on 8/19/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct AddFoodBySearchView: View {
    
    @StateObject var viewModel: AddFoodBySearchViewModel = AddFoodBySearchViewModel()
    
    @State var searchText: String = ""
    @State var searchResult: [FoodServing] = []
    
    var body: some View {
        NavigationView{
            VStack{
                Text("Add Food By Search").font(.title).bold()
                
                TextField(
                    "Search For Food",
                    text: $searchText
                )
                .autocapitalization(.none)
                .disableAutocorrection(true)
                .padding(.top, 20).onSubmit {
                    print("Submit")
                    Task {
                        searchResult = await viewModel.getFoodBySearch(query: searchText)
                    }
                }
                
                ScrollView{
                    ForEach($searchResult, id: \.self){ food in
                        FoodRow(foodServing: food)
                    }
                }
            }.padding(30)
        }
    }
}

class AddFoodBySearchViewModel: ObservableObject{
    let util: FoodUtil = FoodUtil()
    
    func getFoodBySearch(query: String) async -> [FoodServing] {
        var ret: [FoodServing] = []
        let req = FoodSearchRequest(query: query)
        
        do{
            let response = try await util.getFoodBySearch(req: req)
            if response.success && response.body != nil{
                ret = response.body!.products.map({ f in
                    FoodServing(numberOfServings: 1, food: f)
                })
            }else{
                print("response was a success but couldn't store response body as return value")
            }
        }catch {
            print("Error with sending to backend")
        }
        
        return ret
    }
}

#Preview {
    AddFoodBySearchView().environmentObject(User())
}
