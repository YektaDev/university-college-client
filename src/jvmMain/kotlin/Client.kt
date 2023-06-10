import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

internal class Client {
    private companion object {
        const val DEFAULT_PORT = 8080
    }

    private val port = System.getenv("PORT")?.toInt() ?: DEFAULT_PORT
    private val socket = Socket("127.0.0.1", port)
    private val reader = BufferedReader(InputStreamReader(socket.inputStream))
    private val writer = PrintWriter(socket.outputStream, true)

    fun receive(): String {
        val lines = reader.readLine()!!.toInt()
        return buildString {
            repeat(lines) { i ->
                append(reader.readLine())
                if (i < lines - 1) append('\n')
            }
        }
    }

    private fun send(text: String) {
        writer.println(text.lines().size)
        writer.println(text)
    }

    fun sendAverageRequest() = send("average")
    fun sendSortRequest() = send("sort")
    fun sendMaxRequest() = send("max")
    fun sendMinRequest() = send("min")
    fun sendData(data: String) = send(data)
}
