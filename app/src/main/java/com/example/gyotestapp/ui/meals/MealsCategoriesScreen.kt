package com.example.gyotestapp.ui.meals

import android.annotation.SuppressLint
import android.text.Layout.Alignment
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.gyotestapp.R
import com.example.gyotestapp.model.response.MealResponse
import com.example.gyotestapp.ui.theme.GyoTestAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination // needed for ramcosta libraries
@Composable
fun MealsListScreen(
    // navigator: DestinationsNavigator?, // needed for ramcosta libraries
    //navController: NavHostController? = null
) {
    // we re binding the viewModel to the Composable
    val viewModel: GyoTestAppViewModel = viewModel()
    val rememberState = remember { mutableStateOf(emptyList<MealResponse>() ) }
    rememberState.value = viewModel.mealsState.value

    LazyColumn() {
        items(rememberState.value) {
                item -> VisualizeMeal(item)
        }
    }

}

@Composable
fun VisualizeMeal(meal: MealResponse) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp)

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
        MealsListScreen()
    }
}