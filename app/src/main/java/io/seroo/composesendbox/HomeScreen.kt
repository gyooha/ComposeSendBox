/*
package io.seroo.composesendbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers

@Composable
internal fun HomeScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .weight(1f), text = "홈"
                )
                Text(
                    text = "메뉴",
                    modifier = Modifier.clickable {
                        navHostController.navigate("menu") {
                            launchSingleTop = true
                            popUpTo("home")
                        }
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "홈홈홈홈")
        }
        it.calculateBottomPadding()
    }
}*/
