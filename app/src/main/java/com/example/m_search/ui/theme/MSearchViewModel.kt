package com.example.m_search.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_search.data.MedicineSer
import com.example.m_search.network.MSearchApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import android.util.Log


sealed interface MSearchUiState {
    data class Success(val medicines: List<MedicineSer>) : MSearchUiState
    object Error : MSearchUiState
    object  Loading : MSearchUiState
}

class MSearchViewModel: ViewModel() {

    var msearchUiState : MSearchUiState by mutableStateOf(MSearchUiState.Loading)
        private set

    fun getMedicines(ndc: String) {
        viewModelScope.launch {

            msearchUiState = try {
                val listResult = MSearchApi.retrofitService.getMedicines(ndc)
                //Log.d("Test",listResult.toString())
                MSearchUiState.Success(listResult)
            } catch (e: IOException) {
                MSearchUiState.Error
            } catch (e: HttpException) {
                MSearchUiState.Error
            }
        }
    }

//    private val _uiState = MutableStateFlow(MSearchUiState())
//    val uiState: StateFlow<MSearchUiState> = _uiState.asStateFlow()
//
//    fun resetApp() {
//        _uiState.value = MSearchUiState()
//    }
}