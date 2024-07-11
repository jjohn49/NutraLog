import SwiftUI

@main
struct iOSApp: App {
    
    @StateObject var user: User = User()
    
	var body: some Scene {
		WindowGroup {
            ContentView().environmentObject(self.user)
		}
	}
}
