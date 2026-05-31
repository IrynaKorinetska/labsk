package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @SerialName("name") val name: Name,
    @SerialName("independent") val independent: Boolean? = null,
    @SerialName("capital") val capital: List<String>? = emptyList(),
    @SerialName("languages") val languages: Map<String, String>? = emptyMap(),
    @SerialName("flags") val flags: Flags
)

@Serializable
data class Name(
    @SerialName("common") val common: String,
    @SerialName("official") val official: String
)

@Serializable
data class Flags(
    @SerialName("png") val png: String
)