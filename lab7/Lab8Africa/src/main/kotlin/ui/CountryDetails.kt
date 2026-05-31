package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.model.Country

@Composable
fun CountryDetails(country: Country, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Button(onClick = onBack) {
            Text("<- Назад до списку")
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(country.name.official, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Суспільно-політичні дані:", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))

        val isIndependent = if (country.independent == true) "Так" else "Ні"
        Text("Незалежність: $isIndependent", fontSize = 18.sp)

        val langs = country.languages?.values?.joinToString(", ") ?: "Не вказано"
        Text("Державні мови: $langs", fontSize = 18.sp)
    }
}