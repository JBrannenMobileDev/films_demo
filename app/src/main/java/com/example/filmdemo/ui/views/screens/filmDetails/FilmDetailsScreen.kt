package com.example.filmdemo.ui.views.screens.filmDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.filmdemo.data.model.entity.Film
import com.example.filmdemo.data.model.entity.People
import com.example.filmdemo.ui.views.DetailsCategoryLazyRow
import kotlin.reflect.KFunction1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailsScreen(
    navController: NavController,
    film: Film?,
    characters: List<People>,
    onDetailItemSelected: KFunction1<String, Unit>,
) {
    Scaffold(
        topBar = { AppBar(navController, film) },
        content = { Content(it, film, onDetailItemSelected, characters) }
    )
}

@Composable
fun Content(
    padding: PaddingValues,
    film: Film?,
    onDetailItemSelected: KFunction1<String, Unit>,
    characters: List<People>,
) {
    LazyColumn(
        Modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { ReleaseDateText(film) }
        item { DirectorText(film) }
        item { DetailsCategoryLazyRow(title = "Characters", characters = characters, onDetailItemSelected = onDetailItemSelected) }
        item { OpeningCrawlingText(film) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    navController: NavController,
    film: Film?,
) {
    CenterAlignedTopAppBar(
        modifier= Modifier.fillMaxWidth(),
        title = {
            Text(
                text = film?.title ?: "Details",
                color = Color.White,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = {navController.navigateUp()}) {
                Icon(Icons.Filled.ArrowBack, "backIcon", tint = Color.White)
            }
        },
    )
}


@Composable
fun ReleaseDateText(
    film: Film?
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 24.dp),
    ) {
        Text(text = "Release Date  -  ${film?.releaseDate}", color = Color.White)
    }
}

@Composable
fun DirectorText(
    film: Film?
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 24.dp),
    ) {
        Text(text = "Directed By:  ${film?.director}", color = Color.White)
    }
}


@Composable
fun OpeningCrawlingText(
    film: Film?
) {
    Box(Modifier.padding(horizontal = 24.dp)) {
        Text(text = film?.openingCrawl ?: "", color = Color.White, textAlign = TextAlign.Center)
    }
}