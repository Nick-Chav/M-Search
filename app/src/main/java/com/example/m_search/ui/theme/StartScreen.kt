package com.example.m_search.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MSearchTheme
import com.example.m_search.R

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onStartButtonClicked: () -> Unit,
    onInstructionsButtonClicked: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Image(
                painter = painterResource(R.drawable.m_search_logo),
                contentDescription = null,
                modifier = Modifier.width(400.dp)
            )
            Text(
                text = stringResource(R.string.app_description),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.padding_medium)
            )
        ) {
            Button(
                modifier = Modifier.widthIn(min = 250.dp),
                onClick = onStartButtonClicked
            ){
                Text(text = stringResource(R.string.start))
            }
            OutlinedButton(
                modifier = Modifier.widthIn(min = 250.dp),
                onClick = onInstructionsButtonClicked
            ){
                Text(text = stringResource(R.string.instructions))
            }
        }
    }
}
//TODO Fix Back button X interaction
@Composable
@Preview(showBackground = true)
fun StartOrderPreview() {
    MSearchTheme {
        StartScreen(modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_medium)), {}, {})
    }
}