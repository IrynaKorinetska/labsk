package ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import data.CountriesApiClient
import data.model.Country
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    var countries by remember { mutableStateOf(emptyList<Country>()) }
    var displayedCountries by remember { mutableStateOf(emptyList<Country>()) }
    var selectedCountry by remember { mutableStateOf<Country?>(null) }
    var searchedText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                countries = CountriesApiClient.getAfricanCountries()
                displayedCountries = countries
                isLoading = false
            } catch (e: Exception) {
                println("Помилка завантаження: ${e.message}")
                isLoading = false
            }
        }
    }

    Row {
        SidePanel(
            searchedText = searchedText,
            onSearchChanged = { text ->
                searchedText = text
                displayedCountries = if (text.isEmpty()) {
                    countries
                } else {
                    countries.filter {
                        it.name.common.contains(text, ignoreCase = true)
                    }
                }
                selectedCountry = null
            },
            onReset = {
                searchedText = ""
                displayedCountries = countries
                selectedCountry = null
            }
        )

        if (selectedCountry == null) {
            CountriesGrid(
                countries = displayedCountries,
                isLoading = isLoading,
                onCountryClick = { selectedCountry = it }
            )
        } else {
            CountryDetails(
                country = selectedCountry!!,
                onBack = { selectedCountry = null }
            )
        }
    }
}