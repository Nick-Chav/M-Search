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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.compose.MSearchTheme
import com.example.m_search.R
import com.example.m_search.data.DataSource.medicines
import com.example.m_search.data.Medicine

@Composable
fun DisplayResultsScreen(
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier) {
            Text(
                text = stringResource(R.string.original_med),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
            MedicItem(
                medicine = medicines[0],
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                color = MaterialTheme.colorScheme.inversePrimary,
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.results),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )

            LazyColumn(
                modifier = Modifier.height(330.dp)
            ) {
                items(medicines) {
                    MedicItem(
                        medicine = it,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }

        OutlinedButton(
            modifier = Modifier.widthIn(min = 250.dp),
            onClick = onBackButtonClicked
        ){
            Text(text = stringResource(R.string.app_back))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicItem(
    modifier: Modifier = Modifier,
    medicine: Medicine,
    color: Color
) {

    var showDialog by remember { mutableStateOf(false) }

    Card(
        onClick = { showDialog = true },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = color // Set your desired color here
        )
    ) {
        Text(modifier = Modifier.padding(16.dp),
            text = stringResource(medicine.name)
        )
    }
    if (showDialog) {
        DisplayDialog(
            disableDialog = {showDialog = false},
            item = medicine
        )
    }
}

@Composable
fun DisplayDialog(
    item: Medicine,
    disableDialog: () -> Unit
) {

    val context = LocalContext.current
    val name = stringResource(item.name)

    Dialog(onDismissRequest = disableDialog) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(item.name),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
            }
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
            ) {

                Text(text = "Ingredient: ${item.ingredient}", modifier = Modifier.padding(4.dp))
                Text(text = "Dosage: ${item.dosage}", modifier = Modifier.padding(4.dp))
                Text(text = "Intake: ${item.mode}", modifier = Modifier.padding(4.dp))

                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            //TODO adjust for Danish
                            val url =
                                "https://en.wikipedia.org/wiki/${name}"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                ) {
                    // Underlined and italic Wikipedia text
                    Text(
                        text = buildAnnotatedString {
                            append("Wikipedia")
                            addStyle(
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                    fontStyle = FontStyle.Italic,
                                ),
                                start = 0,
                                end = length
                            )
                        }
                    )

                    Spacer(modifier = Modifier.width(4.dp)) // Space between text and icon

                    // Icon indicating it's a clickable link
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Link Icon"
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Column(modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = disableDialog) {
                    Text(stringResource(R.string.return_back))
                }
            }
        }
    }
}

@Composable
@Preview
fun DisplayDialogPreview() {
    MSearchTheme {
        DisplayDialog(Medicine(R.string.test_med, 12, "inject", "10mg", "bibibobo")){

        }
    }
}

@Composable
@Preview(showBackground = true)
fun DisplayResultsPreview() {
    MSearchTheme {
        DisplayResultsScreen(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .fillMaxSize(),
           {},
            //{},{}
        )
    }
}