import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(client: Client, initialStudentData: String, response: String, submitData: (String) -> Unit) {
    MaterialTheme {
        var data by remember { mutableStateOf(TextFieldValue(initialStudentData)) }
        Column(
            modifier = Modifier.padding(horizontal = 8f.dp),
            verticalArrangement = Arrangement.spacedBy(8f.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(Modifier.fillMaxWidth().weight(1f)) {
                TextField(
                    value = data,
                    modifier = Modifier.fillMaxHeight().weight(1f),
                    shape = RoundedCornerShape(topStart = 16f.dp, bottomStart = 16f.dp),
                    onValueChange = { data = it },
                    visualTransformation = { studentDataVisualTransformation(data.annotatedString) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Unspecified,
                        unfocusedIndicatorColor = Color.Unspecified,
                        errorIndicatorColor = Color.Unspecified,
                    )
                )
                Box(
                    modifier = Modifier
                        .background(Color.DarkGray, RoundedCornerShape(topEnd = 16f.dp, bottomEnd = 16f.dp))
                        .padding(16f.dp)
                        .fillMaxHeight()
                        .width(400f.dp)
                ) {
                    val scrollState = remember(response) { ScrollState(0) }
                    Text(
                        text = response,
                        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
                        color = MaterialTheme.colorScheme.inversePrimary,
                        style = MaterialTheme.typography.bodySmall,
                    )
                    VerticalScrollbar(
                        adapter = rememberScrollbarAdapter(scrollState),
                        style = LocalScrollbarStyle.current.copy(
                            unhoverColor = Color.White.copy(.2f),
                            hoverColor = Color.White.copy(.5f)
                        ),
                        modifier = Modifier
                            .padding(end = 8f.dp)
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .zIndex(1f)
                    )
                }
            }
            Button(onClick = { submitData(data.text) }) { Text("Save Changes") }
            Row(horizontalArrangement = Arrangement.spacedBy(8f.dp)) {
                Button("Get average grade", client::sendAverageRequest)
                Button("Get sorted list by grade", client::sendSortRequest)
                Button("Who has the highest grade?", client::sendMaxRequest)
                Button("Who has the lowest grade?", client::sendMinRequest)
            }
        }
    }
}

@Composable
private fun Button(text: String, onClick: () -> Unit) = TextButton(
    onClick = onClick,
    border = ButtonDefaults.outlinedButtonBorder,
) { Text(text) }
