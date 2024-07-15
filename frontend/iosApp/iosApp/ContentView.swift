import SwiftUI
import Shared

struct ContentView: View {
    
    @EnvironmentObject var user: User
    
    var body: some View {
        HomePageView().environmentObject(self.user)
    }
}

#Preview {
    ContentView().environmentObject(User())
}




