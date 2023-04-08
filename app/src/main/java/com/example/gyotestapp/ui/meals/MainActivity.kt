package com.example.gyotestapp.ui.meals

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gyotestapp.UserProfile
import com.example.gyotestapp.model.response.MealResponse
import com.example.gyotestapp.ui.theme.GyoTestAppTheme
import com.example.gyotestapp.ui.theme.lightGreen
import com.example.gyotestapp.userProfileList
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(content = {
            // carichiamo il template custom
            GyoTestAppTheme() {
                //DestinationsNavHost(navGraph = NavGraphs.root)
                UserListScreen()
            }
        })
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination // needed for ramcosta libraries
@Composable
fun UserListScreen(
   // navigator: DestinationsNavigator?, // needed for ramcosta libraries
    //navController: NavHostController? = null
) {
    // we re binding the viewModel to the Composable
    val viewModel: GyoTestAppViewModel = viewModel()
    val rememberState = remember { mutableStateOf(emptyList<MealResponse>() ) }
    rememberState.value = viewModel.mealsState.value

    LazyColumn() {
        items(rememberState.value) {
                item -> Text(text = item.name)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ListUserPreview() {
    GyoTestAppTheme() {
        UserListScreen()
    }
}
