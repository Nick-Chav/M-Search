package com.example.m_search.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MSearchTheme
import com.example.m_search.R

@Composable
fun SelectModeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.mode_selection_title),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 0.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {

            Text(
                text = stringResource(R.string.mode_selection_instructions),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }

        Row() {
            Button(
                modifier = Modifier
                    .heightIn(min = 100.dp)
                    .weight(1f)
                    .padding(10.dp),
                onClick = {},
            ){
                Text(
                    text = stringResource(R.string.mode_manual),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Button(
                modifier = Modifier
                    .heightIn(min = 100.dp)
                    .weight(1f)
                    .padding(10.dp),
                onClick = {}
            ){
                Text(
                    text = stringResource(R.string.mode_scan),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
        OutlinedButton(
            modifier = Modifier.widthIn(min = 250.dp),
            onClick = {}
        ){
            Text(text = stringResource(R.string.app_back))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SelectModePreview() {
    MSearchTheme {
        SelectModeScreen(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .fillMaxSize()
        )
    }
}