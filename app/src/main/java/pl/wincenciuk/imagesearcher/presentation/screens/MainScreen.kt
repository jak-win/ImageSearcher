package pl.wincenciuk.imagesearcher.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pl.wincenciuk.imagesearcher.R
import pl.wincenciuk.imagesearcher.data.a.model.ImageItem
import pl.wincenciuk.imagesearcher.presentation.SearcherViewModel
import pl.wincenciuk.imagesearcher.presentation.navigation.AppScreens

@Composable
fun MainScreen(navController: NavController, viewModel: SearcherViewModel) {
    var searchText by remember { mutableStateOf("fruits") }
    val imageData = viewModel.imagesData.collectAsState(emptyList())
    val dialogState = remember { mutableStateOf(false) }
    val selectedItem = remember { mutableStateOf<ImageItem?>(null) }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = searchText) {
        viewModel.getImages(searchText)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF104977)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .padding(9.dp)
                    .padding(top = 5.dp, bottom = 15.dp),
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(3.dp, Color.White),
                elevation = 15.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(id = R.string.hello_text),
                        modifier = Modifier
                            .padding(4.dp)
                            .padding(top = 5.dp, bottom = 5.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        color = Color.White
                    ) {
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            label = { Text(text = stringResource(id = R.string.txtfld_name)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = {
                                keyboardController?.hide()
                            }),
                            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                            maxLines = 1,
                            modifier = Modifier
                                .padding(10.dp)
                        )

                    }

                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                imageData.value.forEach { response ->
                    response.hits.forEach { item ->
                        ImageItemCard(item = item) {
                            selectedItem.value = item
                            dialogState.value = true
                        }
                    }
                }
            }
        }
        if (dialogState.value) {
            selectedItem.value?.let { item ->
                AlertDialog(
                    onDismissRequest = { dialogState.value = false },
                    title = { Text(text = stringResource(id = R.string.dialog_question)) },
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModel.setSelectedItem(item)
                                navController.navigate(AppScreens.DetailsScreen.name)
                                dialogState.value = false
                            }) {
                            Text(text = stringResource(id = R.string.dialog_pos))
                        }
                    },
                    dismissButton = {
                        Button(onClick = { dialogState.value = false }) {
                            Text(text = stringResource(id = R.string.dialog_neg))
                        }
                    })
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ImageItemCard(
    item: ImageItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 4.dp, vertical = 4.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        onClick = {
            onClick.invoke()
        }
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(item.largeImageURL)
                    .crossfade(true).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            ), startY = 350f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "User: ${item.user}",
                        style = TextStyle(color = Color.White, fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Tags: ${item.tags}",
                        style = TextStyle(color = Color.White, fontSize = 15.sp)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
//    MainScreen()
}
