package com.example.gyotestapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gyotestapp.ui.theme.GyoTestAppTheme
import com.example.gyotestapp.ui.theme.lightGreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(content = {
            // carichiamo il template custom
            GyoTestAppTheme() {
                MainScreen()
            }

        })
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainScreen() {
        Scaffold(topBar = { AppBar() }) {
            Surface(
                color = Color.LightGray,
                modifier = Modifier.fillMaxSize()
            ) {
                ProfileCard()
            }
        }
    }

    @Composable
    fun AppBar() {
        TopAppBar(
            navigationIcon = { Icon(Icons.Default.Person,
                "Home",
                modifier = Modifier.padding(12.dp))
                             },
            title = {Text("Messaging Application Users")}
        )

    }

    @Composable
    fun ProfileCard() {
        Card(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Top),
            elevation = 8.dp
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                ProfilePicture()
                ProfileContent()
            }
        }
    }

    @Composable
    fun ProfilePicture() {
        Card(
            shape = CircleShape,
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colors.lightGreen),
            modifier = Modifier.padding(16.dp),
            elevation = 4.dp

        ) {
            Image(
                bitmap = ImageBitmap.imageResource( id = R.drawable.hisag ),
                contentDescription = "profileImage",
                modifier = Modifier.size(72.dp),
                contentScale = ContentScale.Crop
            )
        }
    }

    @Composable
    fun ProfileContent() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "Hisagi Hyuei",
                color = Color.DarkGray,
                style = MaterialTheme.typography.h4
            )
            Text(
                text = "Active now",
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6
            )
        }
    }

    /*
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
                onValueChange = onTextInput,
                label = { Text("insert text") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Red,
                    backgroundColor = Color.Blue,
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Yellow
                )
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
        GyoTestAppTheme() {
            MainScreen()
        }
    }
}