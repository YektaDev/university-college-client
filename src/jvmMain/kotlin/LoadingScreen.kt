import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun LoadingScreen() = Box(
    modifier = Modifier.background(Color.Black).fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    Text(
        text = "Waiting for the server to go online...",
        modifier = Modifier.fillMaxWidth(),
        color = Color.Cyan,
        style = MaterialTheme.typography.h3,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.ExtraLight,
    )
}
