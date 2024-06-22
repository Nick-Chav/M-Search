package com.example.m_search.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MSearchTheme
import com.example.m_search.R
import com.example.m_search.data.DataSource

@Composable
fun EnterDataScreen(
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
) {

    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.manual_enter_title),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Justify,
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))) {

            Text(
                text = "",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Column(modifier = Modifier.padding(vertical = 40.dp)) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.manual_instructions),
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                    style = MaterialTheme.typography.bodyLarge
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = dimensionResource(R.dimen.padding_small)
                            ),
                        label = { Text("...") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        value = firstNumber,
                        onValueChange = {
                            if(it == "") firstNumber = it
                            else if(it[it.length - 1].isDigit()) firstNumber = it
                        },
                    )
                    Text(text = "-")
                    OutlinedTextField(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = dimensionResource(R.dimen.padding_small)
                            ),
                        label = { Text("...") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        value = secondNumber,
                        onValueChange = {
                            if(it == "") secondNumber = it
                            else if(it[it.length - 1].isDigit()) secondNumber = it
                                        },
                    )
                }


            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_medium)
        )) {
            Button(
                modifier = Modifier.widthIn(min = 250.dp),
                onClick = onNextButtonClicked
            ){
                Text(text = stringResource(R.string.app_next))
            }
            OutlinedButton(
                modifier = Modifier.widthIn(min = 250.dp),
                onClick = onBackButtonClicked
            ){
                Text(text = stringResource(R.string.app_back))
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun EnterDataPreview() {
    MSearchTheme {
        EnterDataScreen(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .fillMaxSize(),
            {},
            {}
        )
    }
}