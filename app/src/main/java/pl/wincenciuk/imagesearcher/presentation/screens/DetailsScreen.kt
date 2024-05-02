package pl.wincenciuk.imagesearcher.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pl.wincenciuk.imagesearcher.R
import pl.wincenciuk.imagesearcher.presentation.SearcherViewModel

@Composable
fun DetailsScreen(viewModel: SearcherViewModel) {
    val selectedItem by viewModel.selectedItem.collectAsState(null)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF104977)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .padding(9.dp)
                    .padding(top = 5.dp, bottom = 5.dp),
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(3.dp, Color.White),
                elevation = 15.dp
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    Text(
                        text = stringResource(id = R.string.detail_header),
                        modifier = Modifier
                            .padding(4.dp)
                            .align(Alignment.CenterHorizontally),
                        color = Color.White,
                        fontSize = 33.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        color = Color.White
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(text = "Username: ${selectedItem?.user}", fontSize = 17.sp)
                            Text(text = "Tags: ${selectedItem?.tags}", fontSize = 17.sp)
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(selectedItem?.largeImageURL)
                        .crossfade(true).build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
            Surface(
                modifier = Modifier
                    .padding(9.dp)
                    .padding(top = 5.dp, bottom = 15.dp)
                    .fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(3.dp, Color.Gray),
                elevation = 15.dp
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(Icons.Outlined.Favorite, contentDescription = null)
                    Text(text = "${selectedItem?.likes}")
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(Icons.Default.Edit, contentDescription = null)
                    Text(text = "${selectedItem?.comments}")
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(Icons.Default.Share, contentDescription = null)
                    Text(text = "${selectedItem?.downloads}")
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
        }
    }
}
