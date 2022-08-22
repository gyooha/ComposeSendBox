package io.seroo.composesendbox

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class SubActivity : ComponentActivity() {

    private val viewModel: SubViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel = viewModel)
        }
    }

    @Composable
    private fun MainScreen(viewModel: SubViewModel) {
        val topNumber by viewModel.topNumber.collectAsState()
        val bottomText by viewModel.bottomText.collectAsState()
        MainScreenWithData(
            topNumber,
            bottomText,
            onClick = viewModel::onClick,
            onClick2 = viewModel::onClick2
        )
    }

    @Composable
    private fun MainScreenWithData(
        topNumber: Int,
        bottomText: String,
        onClick: () -> Unit,
        onClick2: () -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            TopNumber(
                modifier = Modifier,
                number = topNumber,
                onClick = {
                    if (topNumber >= 0) onClick()
                },
            )
            BottomText(bottomText = bottomText, onClick = onClick2)
            Button(
                onClick = {
                    finish()
                }
            ) {
                Text("이전")
            }
        }
    }

    @Composable
    private fun TopNumber(
        modifier: Modifier = Modifier,
        number: Int,
//        bottomText: String,
        onClick: () -> Unit
    ) {
        Log.d("GYH", "TopNumber recompose : ${onClick.hashCode()}")
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
                .background(getRandomColor())
                .doOnClick {
                    onClick()
                }
        ) {
            Text(text = "TopText : ${number}", fontSize = 20.sp, color = Color.Black)
        }
    }

    @Composable
    private fun BottomText(bottomText: String?, onClick: () -> Unit) {
        Log.d("GYH", "BottomText recompose : ${onClick.hashCode()}")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
                .background(getRandomColor())
                .clickable { onClick() }
        ) {
            Text(text = bottomText ?: "", fontSize = 20.sp, color = Color.Black)
        }
    }
}

@Composable
private fun getRandomColor(): Color =
    Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

