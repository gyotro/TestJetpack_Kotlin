package com.example.gyotestapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
                UserListScreen(userProfileList)
            }

        })
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun UserListScreen(userProfile: List<UserProfile>) {
        Scaffold(topBar = { AppBar() }) {
            Surface(
                color = Color.LightGray,
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn {
                   items(userProfile) { userProfileItem ->
                       ProfileCard(userProfileItem)
                   }
                }
                /* Column {
                        userProfile.forEach() {
                            ProfileCard(it)
                        }*/
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
                ProfilePicture(userProfile.drawableUrl, userProfile.status)
                ProfileContent(userProfile.name, userProfile.status)
            }
        }
    }

    @Composable
    fun ProfilePicture(drawableUrl: String, status: Boolean, imageSize: Int = 90){
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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(drawableUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.error),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
                    .size(imageSize.dp)
            )
        }
    }

    @Composable
    fun ProfileContent(name: String, status: Boolean, alignment: Alignment.Horizontal = Alignment.Start) {
        Column(
            modifier = Modifier
//   la commentiamo per evitare che sia mal allineato lo screen UserProfile
//                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = alignment
        ) {
            Text(
                text = name,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h4,
             //   textAlign = TextAlign.Center
            )
            Text(
                text = if (status)
                    "Active now"
                else
                    "Offline",
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6,
                //textAlign = TextAlign.Center
            )
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun UserDetailsScreen(userProfile: UserProfile = userProfileList[0]) {
        Scaffold(topBar = { AppBar() }) {
            Surface(
                color = Color.White,
                modifier = Modifier.fillMaxSize()
            )
            {
                Column(
                    // modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                    //HorizontalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    //horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Start
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    ProfilePicture(userProfile.drawableUrl, userProfile.status, imageSize = 250)
                    ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
                }
            }
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
    fun UserPreview() {
        GyoTestAppTheme() {
            UserDetailsScreen()
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun ListUserPreview() {
        GyoTestAppTheme() {
            UserListScreen(userProfileList)
        }
    }
}