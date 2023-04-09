package com.example.gyotestapp.ui.meals

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gyotestapp.R
import com.example.gyotestapp.model.response.MealResponse
import com.example.gyotestapp.ui.meals.destinations.MealDetailDestination
import com.example.gyotestapp.ui.theme.GyoTestAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination // needed for ramcosta libraries
@Composable
fun MealsListScreen(
    navigator: DestinationsNavigator?, // needed for ramcosta libraries
    //navController: NavHostController? = null
) {
    // we re binding the viewModel to the Composable
    val viewModel: GyoTestAppViewModel = viewModel()
    val rememberState = remember { mutableStateOf(emptyList<MealResponse>() ) }
    rememberState.value = viewModel.mealsState.value

    LazyColumn() {
        items(rememberState.value) {
                item -> VisualizeMeal(item, navigator)
        }
    }

}

@Composable
@Destination
fun MealDetail(item: MealResponse?) {
    Column {
        Card(
            shape = RoundedCornerShape(12.dp),
            elevation = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
//            .padding(start = 15.dp)

        ) {
            Row(
                modifier = Modifier.padding(10.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item!!.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.error),
                    //imageLoader = rememberAsyncImagePainter(meal.imageUrl),
                    contentDescription = item.description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(88.dp)
                        .padding(4.dp)
                        .clip(CircleShape)
                )
                Column(
                    modifier = Modifier.align(
                        alignment = androidx.compose.ui.Alignment.CenterVertically
                    )
                        .padding(10.dp)
                ) {
                    Text(text = item.name,
                        color = Color.Black,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
        Text(text = item!!.description,
            color = Color.Black,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold)
    }
    }


@Composable
fun VisualizeMeal(meal: MealResponse, navigator: DestinationsNavigator?) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navigator!!.navigate(MealDetailDestination(meal))
            }
//            .padding(start = 15.dp)

    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.error),
                //imageLoader = rememberAsyncImagePainter(meal.imageUrl),
                contentDescription = meal.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.align(
                    alignment = androidx.compose.ui.Alignment.CenterVertically
                )
                    .padding(10.dp)
            ) {
                Text(text = meal.name,
                    color = Color.Black,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealsListScreenPreview() {
    GyoTestAppTheme() {
        MealsListScreen(null)
    }
}