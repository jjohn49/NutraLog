import SwiftUI
import Shared

struct ContentView: View {
    
    @State var text = "Not Received Yet"

    
    var body: some View {
        VStack {
            Text(text).onAppear(perform: {
                Task{ @MainActor in
                    let response = try await LogInRequest(username: "j", password: "me").makeRequest()
                    print(response)
                   //text = response.message
                }
            })
        }
    }
}




