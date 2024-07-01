package com.example.m_search.data

import androidx.annotation.StringRes
import com.example.m_search.R

object DataSource {
    val countryListFrom = listOf(
        "United States of America",
    )
    val countryListTo = listOf(
        "Denmark",
    )

    val medicines = listOf(
        Medicine("Heroin", "inject", 12, "10mg", "bibibobo", 2.0),
        Medicine("Heroin", "inject", 12, "10mg", "bibibobo", 2.0),
        Medicine("Heroin", "inject", 12, "10mg", "bibibobo", 2.0),
        Medicine("Heroin", "inject", 12, "10mg", "bibibobo", 2.0),
        Medicine("Heroin", "inject", 12, "10mg", "bibibobo", 2.0),
        Medicine("Heroin", "inject", 12, "10mg", "bibibobo", 2.0),
    )
}

data class Medicine(
    val dosage: String,
    val form: String,
    val id: Long,
    val ingredient: String,
    val name: String,
    val rank: Double
)

