package com.example.gyotestapp.ui.meals

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gyotestapp.R
import com.example.gyotestapp.model.response.MealResponse
import com.example.gyotestapp.ui.meals.destinations.MealDetailDestination
import com.example.gyotestapp.ui.theme.GyoTestAppTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.animations.defaults.NestedNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import kotlin.math.min

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination() // needed for ramcosta libraries
@Composable
fun MealsListScreen(
    navigator: DestinationsNavigator?, // needed for ramcosta libraries
    // navController: NavHostController? = null
    // using DI injection with KOIN
    viewModel: GyoTestAppViewModel = koinViewModel(),
) {
    // we re binding the viewModel to the Composable
    //val viewModel: GyoTestAppViewModel = viewModel()
    //val viewModel: GyoTestAppViewModel = koinViewModel()
    //val viewModel = getViewModel<GyoTestAppViewModel>()
    val rememberState = remember { mutableStateOf(emptyList<MealResponse>()) }

    val navHostEngine = rememberAnimatedNavHostEngine(
        navHostContentAlignment = Alignment.TopCenter,
        rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING, //default `rootDefaultAnimations` means no animations
        /*defaultAnimationsForNestedNavGraph = mapOf(
            NavGraphs.settings to NestedNavGraphDefaultAnimations(
                enterTransition = { fadeIn(animationSpec = tween(2000)) },
                exitTransition = { fadeOut(animationSpec = tween(2000)) }
            ),
            NavGraphs.otherNestedGraph to NestedNavGraphDefaultAnimations.ACCOMPANIST_FADING
        ) */// all other nav graphs not specified in this map, will get their animations from the `rootDefaultAnimations` above.
    )

    rememberState.value = viewModel.mealsState.value

    LazyColumn() {
        items(rememberState.value) { item ->
            VisualizeMeal(item, navigator)
        }
    }

}

@Composable
@Destination()
fun MealDetail(item: MealResponse?, navigator: DestinationsNavigator) {
    // caso in cui si usa solo un valore booleano per il cambio di stato
    /*    var isExpanded by remember { mutableStateOf(false) }
        val imagegeSizeDp: Dp by animateDpAsState(
            targetValue = if (isExpanded) 200.dp else 90.dp
        )*/
    // caso in cui si usa l'enumerable
    // avremmo anche potuto creare la variabile così: var isExpanded = remember { mutableStateOf( ProfileStatus.Normal ) }
    // in questo caso poi va fatto isExpanded.value
    var isExpanded by remember { mutableStateOf(ProfileStatus.Normal) }
    val transition = updateTransition(targetState = isExpanded, label = "")
    val imageSizeDp: Dp by transition.animateDp(
        targetValueByState = { it.size }, label = ""
    )
    val imageColor: Color by transition.animateColor(
        targetValueByState = { it.color }, label = ""
    )
    val imageBorderWidthDp: Dp by transition.animateDp(
        targetValueByState = { it.borderWidth }, label = ""
    )
    // implementiamo lo scroll
    val scrollState: LazyListState = rememberLazyListState()
    // capire bene come si costruisce l'offset
    val offset = min(1f,  1f - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex))
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Card(
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                    width = ProfileStatus.Normal.borderWidth,
                    color = ProfileStatus.Normal.color
                ),
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
                            .size(max(100.dp, 200.dp*offset))
                            .padding(4.dp)
                            .clip(CircleShape)
                    )
                    Column(
                        modifier = Modifier.align(
                            alignment = androidx.compose.ui.Alignment.CenterVertically
                        )
                            .padding(10.dp)
                    ) {
                        Text(
                            text = item.name,
                            color = Color.Black,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Text(
                text = item!!.description,
                color = Color.Black,
                textAlign = TextAlign.Justify,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(15.dp)
            )
            /*Button(
                onClick = {
                    isExpanded =
                        if (isExpanded == ProfileStatus.Extended) ProfileStatus.Normal else ProfileStatus.Extended
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Change state of meal profile picture")
            }*/
            Button(
                onClick = { navigator.navigateUp() },
                shape = CircleShape,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Back")
            }
            val lazyList = (1..100).map { "Item_${it.toString()}" }
            // testiamo il rimpicciolimento dell'immagine in base allo scrolling
            LazyColumn(state = scrollState) {
                items(lazyList) {
                    Text(text = it, modifier = Modifier
                        .padding(30.dp)
                        .align(Alignment.Start))
                }
            }
        }
    }

}


@Composable
@Destination()
fun VisualizeMeal(meal: MealResponse, navigator: DestinationsNavigator?) {
    val isExpanded = remember { mutableStateOf(false) }
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
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8F)
                    .align(
                        alignment = androidx.compose.ui.Alignment.CenterVertically
                    )
                    .padding(10.dp)
            ) {
                Text(
                    text = meal.name,
                    color = Color.Black,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
                // the same as (LocalContentAlpha.provides(ContentAlpha.medium)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = meal.description,
                        color = Color.Black,
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.subtitle2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isExpanded.value) 10 else 3
                    )
                }
            }
            Icon(
                imageVector = if (!isExpanded.value) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                contentDescription = "Expand",
                tint = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(15.dp)
                    .clickable() { isExpanded.value = !isExpanded.value }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealsListScreenPreview() {
    GyoTestAppTheme() {
        // Aggiunta per evitare l'errore nella preview
        MealsListScreen(navigator = EmptyDestinationsNavigator)
    }
}

object NonDismissableDialog : DestinationStyle.Dialog {
    override val properties = DialogProperties(
        dismissOnClickOutside = true,
        dismissOnBackPress = true,
    )
}

enum class ProfileStatus(
    val color: Color,
    val size: Dp,
    val borderWidth: Dp,
) {
    Normal(Color.LightGray, 90.dp, 5.dp),
    Extended(Color.Blue, 200.dp, 12.dp)
}