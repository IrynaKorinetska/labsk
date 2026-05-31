package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SidePanel(
    searchedText: String,
    onSearchChanged: (String) -> Unit,
    onReset: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(0.2f).fillMaxHeight()
            .padding(12.dp).background(Color.LightGray.copy(alpha = 0.2f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Країни Африки", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 20.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().height(60.dp),
            singleLine = true,
            placeholder = { Text("Пошук...") },
            value = searchedText,
            onValueChange = onSearchChanged,
            trailingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Button(onClick = onReset, modifier = Modifier.fillMaxWidth()) {
            Text("Всі країни")
        }
    }
}