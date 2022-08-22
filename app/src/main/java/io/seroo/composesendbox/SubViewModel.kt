package io.seroo.composesendbox

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SubViewModel: ViewModel() {
    val topNumber = MutableStateFlow(0)
    val bottomText = MutableStateFlow("")

    fun onClick() {
        topNumber.value.hashCode()
        topNumber.value = topNumber.value + 1
    }

    fun onClick2() {
        bottomText.value = bottomText.value + "ha"
    }
}