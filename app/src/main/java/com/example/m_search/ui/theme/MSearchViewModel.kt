package com.example.m_search.ui.theme

import androidx.lifecycle.ViewModel
import com.example.m_search.data.MSearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MSearchViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MSearchUiState())
    val uiState: StateFlow<MSearchUiState> = _uiState.asStateFlow()

    fun resetApp() {
        _uiState.value = MSearchUiState()
    }
}