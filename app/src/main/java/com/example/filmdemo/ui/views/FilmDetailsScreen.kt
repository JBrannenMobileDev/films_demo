package com.example.filmdemo.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.filmdemo.data.model.entity.Film

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailsScreen(
    navController: NavController,
    film: Film?,
) {
    Scaffold(
        topBar = {AppBar(navController, film)},
        content = {Content(it, film) }
    )
}

@Composable
fun Content(
    padding: PaddingValues,
    film: Film?
) {
    Column(
        Modifier.padding(32.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ScoreCard(padding, film)
        AddressCard(film)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    navController: NavController,
    film: Film?,
) {
    TopAppBar(
        title = {
            Text(text = film?.producer ?: "Details")
        },
        navigationIcon = {
            IconButton(onClick = {navController.navigateUp()}) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
    )
}

@Composable
fun ScoreCard(
    padding: PaddingValues,
    film: Film?
) {
    Card(Modifier.padding(padding), shape = RoundedCornerShape(32.dp)) {
        Box(
            Modifier
                .width(200.dp)
                .height(150.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Suitability Score", textAlign = TextAlign.Center)
                Text(
                    text = film?.director ?: "",
                    textAlign = TextAlign.Center,
                    fontSize = 48.sp
                )
            }

        }
    }
}

@Composable
fun AddressCard(
    film: Film?
) {
    Card(Modifier.padding(top = 48.dp), shape = RoundedCornerShape(32.dp)) {
        Box(Modifier.wrapContentWidth().height(64.dp).padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = film?.openingCrawl ?: "", textAlign = TextAlign.Center)
        }
    }
}
