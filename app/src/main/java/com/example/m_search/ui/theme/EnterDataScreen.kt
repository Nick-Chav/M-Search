package com.example.m_search.ui.theme

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.compose.MSearchTheme
import com.example.m_search.R
import com.example.m_search.data.MedicineSer

@Composable
fun EnterDataScreen(
    modifier: Modifier = Modifier,
    onNextButtonClicked: (String) -> Unit,
    onBackButtonClicked: () -> Unit,
) {

    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

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
                            .padding(
                                horizontal = dimensionResource(R.dimen.padding_small)
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
                            .padding(
                                horizontal = dimensionResource(R.dimen.padding_small)
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

                Text(
                    text = buildAnnotatedString {
                        append(stringResource(R.string.need_help))
                        addStyle(
                            style = SpanStyle(
                                textDecoration = TextDecoration.Underline,
                                fontStyle = FontStyle.Italic,
                            ),
                            start = 0,
                            end = length
                        )
                    },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_small))
                        .clickable { showDialog = true },
                    style = MaterialTheme.typography.labelSmall,

                )


            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_medium)
        )) {
            Button(
                modifier = Modifier.widthIn(min = 250.dp),
                onClick = { onNextButtonClicked("${firstNumber}-${secondNumber}") }
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
    if (showDialog) {
        DisplayDialogNDC(
            disableDialog = {showDialog = false}
        )
    }
}

@Composable
fun DisplayDialogNDC(
    disableDialog: () -> Unit
) {

    val context = LocalContext.current

    Dialog(onDismissRequest = disableDialog) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.ndc_explanation),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(20.dp),

                )
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