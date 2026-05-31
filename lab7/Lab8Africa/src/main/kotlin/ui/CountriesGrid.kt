package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.model.Country
import loadPicture

@Composable
fun CountriesGrid(countries: List<Country>, isLoading: Boolean, onCountryClick: (Country) -> Unit) {
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 250.dp),
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(countries) { country ->
                Card(
                    modifier = Modifier.fillMaxWidth().height(120.dp).clickable { onCountryClick(country) },
                    elevation = 4.dp
                ) {
                    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {

                        // Ось тут ми просто залишили Image без try-catch
                        Image(
                            bitmap = loadPicture(country.flags.png),
                            contentDescription = "Flag of ${country.name.common}",
                            modifier = Modifier.size(80.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(country.name.common, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text("Столиця: ${country.capital?.firstOrNull() ?: "Немає"}")
                        }
                    }
                }
            }
        }
    }
}