package io.seroo.composesendbox

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {
    val uiState = MutableStateFlow(ViewModelState())

    fun onClick() {
        uiState.value.hashCode()
        uiState.value = uiState.value.copy(topNumber = uiState.value.topNumber + 1)
    }

    fun onClick2() {
        uiState.value = uiState.value.copy(bottomText = uiState.value.bottomText + "ha")
    }
}

@Stable
data class ViewModelState(var topNumber: Int = 0, val bottomText: String = "hahaha")