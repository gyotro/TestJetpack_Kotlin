package com.example.gyotestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent( content = {
            MainScreen()
        })
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = MainViewModel()) {
    val mutableList = viewModel.mutableLiveDataList.observeAsState(mutableListOf<String>(""))
    val mutablelStateValue = viewModel.textField.observeAsState("")
    Surface(color = Color.DarkGray,
        modifier = Modifier.fillMaxSize()
    ) {
        SetColumns( mutableList.value,
            buttonClick = { viewModel.addValueToList(mutablelStateValue.value)
                viewModel.onTextChanged("")
                 },
            mutablelStateValue = mutablelStateValue.value
        ) { // definizione della lambda di quando inseriamo testo
                input: String -> viewModel.onTextChanged(input)
        }
    }
}

@Composable
fun SetColumns(
    mutableList: MutableList<String>,
    buttonClick: () -> Unit,
    mutablelStateValue: String,
    onTextInput: (String) -> Unit
               ) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        mutableList.forEach { textToDisplay ->
            DisplayText(textToDisplay)
        }
        TextField(
            value = mutablelStateValue,
            onValueChange = onTextInput
        )
        Button(
            onClick = buttonClick
        ) {
            Text("Add new Item",
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
        }
    }
}


@Composable
fun DisplayText(TextToDisplay: String) {
    Text(TextToDisplay,
        color = Color.Yellow,
        style = MaterialTheme.typography.h4)
}
/*
@Composable
fun HorizontalColoredBar(colorSurf: Color = Color.Black, colorText: Color = Color.Blue) {
    Surface(
        modifier = Modifier.wrapContentSize(
            //align = Alignment.CenterEnd
        ), // prende solo lo spazio di cui ha bisogno
        color = colorSurf
    )
    {
        Text(
            text = "Text",
            color = colorText,
            style = MaterialTheme.typography.h4
        )
    }
}


@Composable
fun GreetingText(name: String) {
    Text(text = "Hello $name Sbrabazz!",
        modifier = Modifier
      //      .fillMaxSize() // occupa tutto lo schermo
            .width(80.dp)
            .height(200.dp)
            .clickable( onClickLabel = "clikkami ", onClick =  {  })
            .padding(start = 24.dp, top = 10.dp),
        style = MaterialTheme.typography.button,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun GreetingText2(name: String) {
    Text(text = "Hello $name Sbrabazz!",
        modifier = Modifier
            //      .fillMaxSize() // occupa tutto lo schermo
            .width(80.dp)
            .height(200.dp)
            .clickable( onClickLabel = "clikkami ", onClick =  {  })
            .padding(start = 24.dp, top = 10.dp),
        style = MaterialTheme.typography.h3,
        fontWeight = FontWeight.Bold,
        color = Color.Red
    )
}

@Composable
fun GreetingButton() {
    Button(onClick = { },content =  {
        GreetingText("Button")
        GreetingText2("Another Button")
    })
}
*/
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}