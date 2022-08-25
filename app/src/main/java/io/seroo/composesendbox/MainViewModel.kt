package io.seroo.composesendbox

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {
    val uiState = MutableStateFlow(ViewModelState())

    fun onClick() {
        uiState.value.hashCode()
//        uiState.value = uiState.value.copy(topNumber = uiState.value.topNumber + 1)
    }

    fun onClick2() {
        uiState.value = uiState.value.copy(bottomText = uiState.value.bottomText + "ha")
    }

    fun onClick3() {
//        uiState.value = uiState.value.copy(testable = WrappedList(uiState.value.testable.testable + listOf("A")))
        uiState.value = uiState.value.copy(testable = uiState.value.testable + listOf("A"))
    }
}

data class ViewModelState(
    var topNumber: InherentlyUnstableClass = InherentlyUnstableClass(),
    val bottomText: String = "hahaha",
    val testable: List<String> = listOf("A")
)

data class InherentlyUnstableClass(var topNumber: Int = 0)

@Immutable
data class WrappedList(
    val testable: List<String>
)

enum class Genre {
    A,
    B,
    C
}