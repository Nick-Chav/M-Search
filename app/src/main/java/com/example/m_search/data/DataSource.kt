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
        Medicine(R.string.test_med, 12, "inject", "10mg", "bibibobo"),
        Medicine(R.string.test_med, 12, "inject", "10mg", "bibibobo"),
        Medicine(R.string.test_med, 12, "inject", "10mg", "bibibobo"),
        Medicine(R.string.test_med, 12, "inject", "10mg", "bibibobo"),
        Medicine(R.string.test_med, 12, "inject", "10mg", "bibibobo"),
        Medicine(R.string.test_med, 12, "inject", "10mg", "bibibobo"),
        Medicine(R.string.test_med, 12, "inject", "10mg", "bibibobo")
    )
}

data class Medicine(
    @StringRes val name: Int,
    val id: Int,
    val mode: String,
    val dosage: String,
    val ingredient: String,
)