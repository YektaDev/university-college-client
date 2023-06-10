import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.concurrent.thread

fun main() {
    var client: Client? by mutableStateOf(null)
    // By contract, this is filled by the first response of the server
    var studentsData: String? by mutableStateOf(null)
    // By contract, this is filled by all responses of the server other than the first one
    var serverResponse by mutableStateOf("")
    thread {
        while (client == null) {
            client = try {
                Client()
            } catch (_: Exception) {
                null
            }
        }
    }
    thread {
        while (true) {
            val c = client ?: continue
            when (studentsData) {
                null -> studentsData = c.receive()
                else -> serverResponse = c.receive()
            }
        }
    }
    application {
        Window(onCloseRequest = ::exitApplication, title = "College Client") {
            val c = client
            val data = studentsData
            when {
                c != null && data != null -> MainScreen(c, data, serverResponse, c::sendData)
                else -> LoadingScreen()
            }
        }
    }
}
