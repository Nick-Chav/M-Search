package com.example.m_search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.m_search.ui.theme.MSearchViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.m_search.ui.theme.SelectCountryScreen
import com.example.m_search.ui.theme.SelectModeScreen
import com.example.m_search.ui.theme.StartScreen


enum class MSearchScreen {
    START,
    SELECT_COUNTRY,
    SELECT_MODE,

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = stringResource(R.string.app_back)
                    )
                }
            }
        }
    )
}


@Composable
fun MSearchApp(
    viewModel: MSearchViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MSearchScreen.valueOf(
        backStackEntry?.destination?.route ?: MSearchScreen.START.name
    )

    Scaffold(
        topBar = {
            CupcakeAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.popBackStack(MSearchScreen.START.name, inclusive = false) }
            )
    }) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MSearchScreen.START.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MSearchScreen.START.name) {
                StartScreen(
                    modifier = Modifier.fillMaxSize(),
                    onStartButtonClicked = {
                        navController.navigate(MSearchScreen.SELECT_COUNTRY.name)
                    }
                )
            }
            composable(route = MSearchScreen.SELECT_COUNTRY.name) {
                SelectCountryScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    onNextButtonClicked = {
                        navController.navigate(route = MSearchScreen.SELECT_MODE.name)
                    },
                    onBackButtonClicked = {
                        navController.navigate(route = MSearchScreen.START.name)
                    }
                )
            }
            composable(route = MSearchScreen.SELECT_MODE.name) {
                SelectModeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    onManualButtonClicked = {},
                    onScanButtonClicked = {},
                    onBackButtonClicked = {
                        navController.navigate(route = MSearchScreen.SELECT_COUNTRY.name)
                    }
                )
            }
        }
    }
}