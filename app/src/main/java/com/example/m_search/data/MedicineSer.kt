package com.example.m_search.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicineSer(
    @SerialName(value = "Name")
    val name: String,
    @SerialName(value = "ID")
    val id: Long,
    @SerialName(value = "Ingredient")
    val ingredient: String,
    @SerialName(value = "Form")
    val form: String,
    @SerialName(value = "Dosage")
    val dosage: String,
    @SerialName(value = "Rank")
    val rank: Double,
)
