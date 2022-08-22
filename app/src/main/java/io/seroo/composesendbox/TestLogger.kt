package io.seroo.composesendbox

import android.util.Log
import androidx.compose.runtime.Immutable

@Immutable
interface TestLogger {
    fun onClickOne()
    fun onClickTwo(what: String)
}

class TestLoggerImpl: TestLogger {
    override fun onClickOne() {
        Log.d("GYH", "onClickOne -")
    }

    override fun onClickTwo(what: String) {
        Log.d("GYH", "onClickTwo - $what")
    }
}