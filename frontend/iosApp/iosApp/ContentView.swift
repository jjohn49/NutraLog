import SwiftUI
import Shared

struct ContentView: View {
    
    @State var util : RequestUtil = RequestUtil()
    
    @State var text: String = ""

    
    var body: some View {
        VStack {
            Text(text).onAppear(perform: {
                Task{ @MainActor in
                    let response = try await util.sendLoginRequest(req: LogInRequest(username: "j", password: "me"))
                    text = response.message
                    print(response)
                   //text = response.message
                }
            })
        }
    }
}




