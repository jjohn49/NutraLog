import SwiftUI
import Shared

struct ContentView: View {
    
    @EnvironmentObject var user: User
    
    var body: some View {
        HomePageView().environmentObject(self.user).onAppear(perform: {
            Task{
                
                //This is just for testing
//                await user.wipeLocalDB()
                
                let response = await user.getUserFromLocalRepo()
                
                let _ = try await user.set(response: response)
            }
        })
    }
}

#Preview {
    ContentView().environmentObject(User())
}




