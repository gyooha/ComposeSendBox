package io.seroo.composesendbox

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestScreen(viewModel = viewModel)
        }
    }
}

@Composable
private fun TestScreen(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    TestScreenWithUIState(
        uiState = uiState,
        onClick = viewModel::onClick,
        onClick2 = viewModel::onClick2
    )
}

@Composable
private fun TestScreenWithUIState(
    uiState: ViewModelState,
    onClick: () -> Unit,
    onClick2: () -> Unit
) {
    val topNumber = remember(uiState.topNumber) {
        uiState.topNumber
    }

    val testLogger: TestLogger = remember {
        TestLoggerImpl()
    }

    Column() {
        Test1(
            number = topNumber,
            onClick = {
                if (topNumber >= 0) {
                    onClick()
                    testLogger.onClickOne()
                }
            }
        )

        Test2(
            text = uiState.bottomText,
            onClick = onClick2
        )
    }
}

@Composable
private fun Test1(number: Int, onClick: () -> Unit) {
    Log.d("Test1", "onClick - ${onClick.hashCode()}")
    Text(
        modifier = Modifier
            .background(getRandomColor())
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(top = 16.dp, bottom = 16.dp),
        text = number.toString(),
        color = Color.White
    )
}

@Composable
private fun Test2(text: String, onClick: () -> Unit) {
    Log.d("Test2", "onClick - ${onClick.hashCode()}")
    Text(
        modifier = Modifier
            .background(getRandomColor())
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(top = 16.dp, bottom = 16.dp),
        text = text,
        color = Color.White
    )
}
/*
    @Composable
    private fun MainScreen(viewModel: MainViewModel) {
        val testLogger: TestLogger = remember {
            TestLoggerImpl()
        }

        val uiState by viewModel.uiState.collectAsState()
        MainScreenWithData(
            testLogger = testLogger,
            uiState = uiState,
            onClick = viewModel::onClick,
            onClick2 = viewModel::onClick2
        )
    }

    @Composable
    private fun MainScreenWithData(
        testLogger: TestLogger,
        uiState: ViewModelState?,
        onClick: () -> Unit,
        onClick2: () -> Unit
    ) {
        val context = LocalContext.current
        val topNumber by remember(uiState?.topNumber) {
            derivedStateOf { uiState?.topNumber ?: 0 }
        }

        val bottomText by remember(uiState?.bottomText) {
            derivedStateOf { uiState?.bottomText ?: "" }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
//            TopNumber(number = topNumber, onClick = { if (uiState?.topNumber ?: 0 >= 0) onClick.invoke() })
            TopNumber(
                number = topNumber,
                onClick = {
                    if (topNumber >= 0) onClick()
                    testLogger.onClickOne()
                }
            )
            BottomText(bottomText = bottomText) {
                onClick2()
                testLogger.onClickTwo(bottomText)
            }
            Button(
                onClick = {
                    startActivity(Intent(context, SubActivity::class.java))
                }
            ) {
                Text("다음")
            }
        }
    }

    @Composable
    private fun TopNumber(
        number: Int,
        onClick: () -> Unit
    ) {
        Log.d("GYH", "TopNumber recompose : ${onClick.hashCode()}")
        Box(
            modifier = Modifier
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
    private fun BottomText(
        bottomText: String,
        onClick: () -> Unit
    ) {
        Log.d("GYH", "BottomText recompose : ${onClick.hashCode()}")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
                .background(getRandomColor())
                .clickable {
                    onClick()
                }
        ) {
            Text(text = bottomText, fontSize = 20.sp, color = Color.Black)
        }
    }
}
*/

@Composable
private fun getRandomColor(): Color =
    Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
