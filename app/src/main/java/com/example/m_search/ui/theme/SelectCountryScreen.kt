package com.example.m_search.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MSearchTheme
import com.example.m_search.R
import com.example.m_search.data.DataSource

@Composable
fun SelectCountryScreen(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.country_selection_title),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(bottom = 0.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {

                Text(
                    text = stringResource(R.string.country_selection_instructions),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )

                Column(modifier = Modifier.padding(vertical = 40.dp)) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(R.string.select_from),
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    DropDown(DataSource.countryListFrom)
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                    Text(
                        text = stringResource(R.string.select_to),
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    DropDown(DataSource.countryListTo)
                }
            }

            Column() {
                Button(
                    modifier = Modifier.widthIn(min = 250.dp),
                    onClick = {}
                ){
                    Text(text = stringResource(R.string.app_next))
                }
                Button(
                    modifier = Modifier.widthIn(min = 250.dp),
                    onClick = {}
                ){
                    Text(text = stringResource(R.string.app_back))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(countryList: List<String>) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(countryList[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded}
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)}
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            countryList.forEachIndexed{ index, text ->
                DropdownMenuItem(
                    text = { Text(text = text) },
                    onClick = {
                        selectedOption = countryList[index]
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SelectCountryPreview() {
    MSearchTheme {
        SelectCountryScreen(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .fillMaxSize()
        )
    }
}