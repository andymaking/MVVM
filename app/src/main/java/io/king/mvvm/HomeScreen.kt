package io.king.mvvm

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import io.king.mvvm.ui.home.HomeViewModel
import io.king.mvvm.data.api.model.Character

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val state by homeViewModel.state.collectAsState()
    
    LazyVerticalGrid(cells = GridCells.Fixed(2)){
        if (state.isEmpty()){
            item{
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }
        items(state){character: Character ->
            CharacterImageCard(character = character)
        }
        
    }
}

@Composable
fun CharacterImageCard(character: Character) {
    val imagerPainter = rememberImagePainter(data = character.image)
    
    Card(shape = MaterialTheme.shapes.medium, modifier = Modifier
        .padding(16.dp)
        .height(200.dp)) {
        Image(painter = imagerPainter, contentDescription = null, modifier = Modifier
            .fillMaxSize(), contentScale = ContentScale.FillBounds)
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom){
            Column(verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.onSurface.copy(.3f))
                .padding(5.dp)) {
                Text(text = "Real Name: ${character.actor}", color = Color.White, fontWeight = FontWeight.Bold)
                Text(text = "Actor Name: ${character.name}", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

