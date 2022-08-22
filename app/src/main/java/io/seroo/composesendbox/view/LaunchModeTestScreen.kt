package io.seroo.composesendbox.view

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.seroo.composesendbox.MainActivity
import io.seroo.composesendbox.task.A
import io.seroo.composesendbox.task.B
import io.seroo.composesendbox.task.C
import io.seroo.composesendbox.task.D

@Composable
fun LaunchModeTestScreen(title: String) {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current
    DisposableEffect(key1 = true, effect = {
        val mngr = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val callback = object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)

                mngr.appTasks
                    .forEach {
                        Log.e(
                            "GYH",
                            "${it.taskInfo.taskId}, ${it.taskInfo.numActivities}, ${it.taskInfo.baseActivity}, ${it.taskInfo.topActivity}"
                        )
                    }
            }
        }
        lifecycle.lifecycle.addObserver(callback)
        onDispose {
            lifecycle.lifecycle.removeObserver(callback)
        }
    })
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = "current : $title",
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(title = "main") {
            val intent = Intent(context, MainActivity::class.java)

            context.startActivity(intent)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(title = "A") {
            val intent = Intent(context, A::class.java)
                .apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }

            context.startActivity(intent)
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(title = "B") {
            val intent = Intent(context, B::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(title = "C") {
            val intent = Intent(context, C::class.java)
            context.startActivity(intent)
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(title = "D") {
            val intent = Intent(context, D::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_DOCUMENT
            }
            context.startActivity(intent)
        }
    }
}

@Composable
private fun Button(title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(Color.Gray)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = title,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}