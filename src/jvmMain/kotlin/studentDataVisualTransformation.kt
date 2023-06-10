import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

private val default = SpanStyle(color = Color.DarkGray, fontWeight = FontWeight.ExtraLight)
private val styles = arrayOf(
    SpanStyle(color = Color(0xff8D7B68), fontWeight = FontWeight.Bold),
    SpanStyle(color = Color(0xff27374D), fontWeight = FontWeight.Bold),
    SpanStyle(color = Color(0xff362FD9), fontWeight = FontWeight.Normal),
    SpanStyle(color = Color(0xffF99417), fontWeight = FontWeight.Normal),
    SpanStyle(color = Color(0xff9A208C), fontWeight = FontWeight.Normal),
    SpanStyle(color = Color(0xff9A208C), fontWeight = FontWeight.Normal),
    SpanStyle(color = Color(0xff9A208C), fontWeight = FontWeight.Normal),
    SpanStyle(color = Color(0xff9A208C), fontWeight = FontWeight.Normal),
    SpanStyle(color = Color(0xff9A208C), fontWeight = FontWeight.Normal),
)

internal fun studentDataVisualTransformation(text: AnnotatedString): TransformedText = TransformedText(
    offsetMapping = OffsetMapping.Identity,
    text = AnnotatedString.Builder().run {
        val lines = text.lines()
        lines.forEachIndexed { lineIndex, line ->
            val tokens = line.split(",")
            tokens.forEachIndexed { index, s ->
                styles.getOrNull(index)?.let { pushStyle(it) } ?: pushStyle(default)
                append(s)
                if (index != tokens.lastIndex) {
                    pushStyle(default)
                    append(',')
                }
            }
            if (lineIndex != lines.lastIndex) append('\n')
        }
        toAnnotatedString()
    },
)

