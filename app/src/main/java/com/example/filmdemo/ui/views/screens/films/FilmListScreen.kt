package com.example.filmdemo.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.filmdemo.data.model.entity.Film
import com.example.filmdemo.ui.views.commonComposables.CenterProgressIndicator
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KFunction1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListScreen(
    navController: NavController,
    filmsSource: Flow<PagingData<Film>>?,
    onItemClicked: KFunction1<Film, Unit>,
    onErrorLoading: KFunction1<String, Unit>,
    onRefreshSelected: () -> Unit,
) {
    val lazyFilmItems: LazyPagingItems<Film>? = filmsSource?.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Start Wars Movies",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
                actions = {
                    // search icon
                    TopAppBarActionButton(
                        imageVector = Icons.Outlined.Refresh,
                        description = "Search",
                        onClick = onRefreshSelected,
                    )
                }
            )
        }, content = {
            if(lazyFilmItems != null) {
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Transparent)
                ) {
                    items(items = lazyFilmItems) { film ->
                        FilmListItem(navController = navController, film = film, onItemClicked = onItemClicked)
                    }

                    lazyFilmItems.apply {
                        when {
                            loadState.mediator?.refresh is LoadState.Loading -> {
                                item { CenterProgressIndicator(Modifier.fillMaxSize().padding(top = 250.dp)) }
                            }
                            loadState.mediator?.append is LoadState.Loading -> {
                                item { CenterProgressIndicator(Modifier.fillMaxSize().padding(top = 250.dp)) }
                            }
                            loadState.mediator?.refresh is LoadState.Error -> {
                                val error = loadState.refresh as LoadState.Error
                                onErrorLoading(error.error.message ?: "")
                            }
                            loadState.mediator?.append is LoadState.Error -> {
                                val error = loadState.append as LoadState.Error
                                onErrorLoading(error.error.message ?: "")
                            }
                        }
                    }
                }
            }
        })
}

@Composable
fun TopAppBarActionButton(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(imageVector = imageVector, contentDescription = description, tint = Color.White)
    }
}
