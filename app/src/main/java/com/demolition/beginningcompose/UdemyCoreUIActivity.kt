package com.demolition.beginningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class UdemyCoreUIActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = MainViewModel()) {
    val textListState = remember {
        mutableStateListOf<String>("Rupu")
    } /* here we are avoiding internal state management by placing the state outside of the
            composable which takes part in recomposition*/

//    var textFieldState by remember {
//        mutableStateOf("")
//    }
      val textFieldState = viewModel.textListState.observeAsState()
    val btnTextState = viewModel.btnTextState.observeAsState()
    DemoTestUI(textListState, {
        viewModel.onButtonClicked(textFieldState.value ?: "")
    }, textFieldState.value, btnTextState.value) {
        viewModel.onTextChanged(it)
    }
}

@Composable
fun DemoTestUI(
    nameList: List<String>,
    buttonClick: () -> Unit,
    textForField: String?,
    textForBtn: String?,
    onTextChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        for (txt in nameList) {
            Text(text = txt)
        }

        TextField(value = textForField ?: "", onValueChange = onTextChange)
        Button(onClick = buttonClick) {
            Text(textForBtn ?: "")
        }
    }
}

@Preview
@Composable
fun Preview() {
    MainScreen()
}