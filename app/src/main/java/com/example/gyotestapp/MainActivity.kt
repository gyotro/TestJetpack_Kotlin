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
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gyotestapp.ui.theme.GyoTestAppTheme
import com.example.gyotestapp.ui.theme.lightGreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(content = {
            // carichiamo il template custom
            GyoTestAppTheme() {
                MainScreen(userProfileList)
            }

        })
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainScreen(userProfile: List<UserProfile>) {
        Scaffold(topBar = { AppBar() }) {
            Surface(
                color = Color.LightGray,
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    userProfile.forEach() {
                        ProfileCard(it)
                    }
                }

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
    fun ProfileCard(userProfile: UserProfile) {
        Card(
            modifier = Modifier
                .padding(top = 10.dp,
                    start = 10.dp,
                    bottom = 10.dp ,
                    end = 10.dp)
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Top),
            elevation = 8.dp
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                ProfilePicture(userProfile.drawable, userProfile.status)
                ProfileContent(userProfile.name, userProfile.status)
            }
        }
    }

    @Composable
    fun ProfilePicture(drawable: Int, status: Boolean) {
        Card(
            shape = CircleShape,
            border = BorderStroke(
                width = 2.dp,
                color = if (status)
                    MaterialTheme.colors.lightGreen
                else
                    Color.Red
        ),
            modifier = Modifier.padding(16.dp),
            elevation = 4.dp

        ) {
            /* version with normal Image
            Image(
                bitmap = ImageBitmap.imageResource( id = drawable ),
                contentDescription = "profileImage",
                modifier = Modifier.size(72.dp),
                contentScale = ContentScale.Crop
            )*/
            // version using Coil Library
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.tiktok.com%2Fapi%2Fimg%2F%3FitemId%3D7172350438694604059%26location%3D0%26aid%3D1988&tbnid=zF4DtBSvrBZ2UM&vet=12ahUKEwjelrmYxob-AhXyhP0HHQ9pC0AQMygpegUIARCZAg..i&imgrefurl=https%3A%2F%2Fwww.tiktok.com%2Fdiscover%2FSh%25C5%25ABhei-Hisagi---Bleach&docid=GcAj6FRy0P6_pM&w=720&h=720&q=hisagi%20bleach&ved=2ahUKEwjelrmYxob-AhXyhP0HHQ9pC0AQMygpegUIARCZAg")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.hisag),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
            )
        }
    }

    @Composable
    fun ProfileContent(name: String, status: Boolean) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = name,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h4
            )
            Text(
                text = if (status)
                    "Active now"
                else
                    "Offline",
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
            MainScreen(userProfileList)
        }
    }
}