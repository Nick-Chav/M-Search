package com.example.m_search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.m_search.ui.theme.MSearchViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost


enum class MSearchScreen {
    START,
}

@Composable
fun MSearchApp(
    viewModel: MSearchViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold() {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MSearchScreen.START.name,
            modifier = Modifier.padding(innerPadding)
        ) {

        }
    }
}