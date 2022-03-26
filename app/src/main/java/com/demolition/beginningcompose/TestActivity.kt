package com.demolition.beginningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fontFamily = FontFamily(
            Font(R.font.lexend_thin, FontWeight.Thin),
            Font(R.font.lexend_extralight, FontWeight.ExtraLight),
            Font(R.font.lexend_light, FontWeight.Light),
            Font(R.font.lexend_regular, FontWeight.Normal),
            Font(R.font.lexend_medium, FontWeight.Medium),
            Font(R.font.lexend_semibold, FontWeight.SemiBold),
            Font(R.font.lexend_bold, FontWeight.Bold),
            Font(R.font.lexend_extrabold, FontWeight.ExtraBold)
        )
        setContent {
            ScrollableLazyListLikeRV()
        }
    }
}
fun fontFamily():FontFamily = FontFamily(
    Font(R.font.lexend_thin, FontWeight.Thin),
    Font(R.font.lexend_extralight, FontWeight.ExtraLight),
    Font(R.font.lexend_light, FontWeight.Light),
    Font(R.font.lexend_regular, FontWeight.Normal),
    Font(R.font.lexend_medium, FontWeight.Medium),
    Font(R.font.lexend_semibold, FontWeight.SemiBold),
    Font(R.font.lexend_bold, FontWeight.Bold),
    Font(R.font.lexend_extrabold, FontWeight.ExtraBold)
)

@Composable
fun TextStyling(text: String, fontFamily: FontFamily = fontFamily()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF32DE84))
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontSize = 40.sp
                    )
                ) {
                    append(text.elementAt(0))
                }
                append(text.substring(1))
            },
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = fontFamily,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Start,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun TextFieldButtonSnackBar() {
    val scaffoldState = rememberScaffoldState()
    val textFieldState = remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            TextField(
                value = textFieldState.value,
                label = {
                    Text("Enter Food Name")
                },
                onValueChange = { newText: String ->
                    textFieldState.value = newText
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(15.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "${textFieldState.value}, kha kha kha, chuse chuse kha, gile gile kha \n kha naaaaa..",
                            duration = SnackbarDuration.Long
                        )
                    }
                }) {
                    Text("Order Now")
                }
            }

        }
    }
}

@Composable
fun ScrollableList(){
     val scrollableState = rememberScrollState()
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.verticalScroll(state = scrollableState)){
        for ( i in 1..50){
            TextStyling( text = "I love Android" )
        }
    }
}

@Composable
fun ScrollableLazyList() {
    LazyColumn {
        items(3000){
            TextStyling( text = "I love Android $it" )
        }
    }
}

@Composable
fun ScrollableLazyListLikeRV(){
    LazyColumn {
        itemsIndexed(
            listOf("I", "Love", "Rupu", "and", "Android")
        ){ index, string ->
            TextStyling( text = " $string $index" )
        }
    }
}

