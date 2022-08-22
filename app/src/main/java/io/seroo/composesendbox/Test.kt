package io.seroo.composesendbox

import android.os.SystemClock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

fun Modifier.doOnClick(
    targetMillis: Long = 500L,
    rippleColor: Color = Color.Cyan,
    isEnabled: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {
    var lastClickedTime by remember { mutableStateOf(0L) }
    clickable(
        indication = rememberRipple(color = rippleColor),
        interactionSource = remember { MutableInteractionSource() },
        enabled = isEnabled,
    ) {
        val now = SystemClock.uptimeMillis()
        if (now - lastClickedTime > targetMillis) {
            onClick()
            lastClickedTime = SystemClock.uptimeMillis()
        }
    }
}
