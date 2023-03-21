package com.example.filmdemo.ui.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.filmdemo.data.model.entity.Film

@Composable
fun DriverListScreen(
    navController: NavController,
    films: List<Film>,
    totalSS: Double,
    onItemClicked: (item: Film) -> Unit
) {
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Drivers - Total SS = $totalSS")
                },
            )
        }, content = {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                itemsIndexed(films){ _, item ->
                    DriverListItem(navController = navController, film = item, onItemClicked)
                }
            }
        })
}
