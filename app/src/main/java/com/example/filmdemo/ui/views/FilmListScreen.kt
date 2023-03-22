package com.example.filmdemo.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.filmdemo.data.model.entity.Film
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KFunction1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListScreen(
    navController: NavController,
    filmsSource: Flow<PagingData<Film>>?,
    onItemClicked: KFunction1<Film, Unit>
) {
    val lazyFilmItems: LazyPagingItems<Film>? = filmsSource?.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Hypori Film Demo")
                },
            )
        }, content = {
            if(lazyFilmItems != null) {
                LazyColumn(modifier = Modifier.padding(it).fillMaxWidth().fillMaxHeight()) {
                    items(items = lazyFilmItems) { film ->
                        FilmListItem(navController = navController, film = film, onItemClicked = onItemClicked)
                    }

                    lazyFilmItems.apply {
                        when {
                            loadState.mediator?.refresh is LoadState.Loading -> {
                                item { CenterProgressIndicator() }
                            }
                            loadState.mediator?.append is LoadState.Loading -> {
                                item { CenterProgressIndicator() }
                            }
                            loadState.mediator?.refresh is LoadState.Error -> {
                                val error = loadState.refresh as LoadState.Error
                                item { ErrorView(error.error.localizedMessage!!, modifier = Modifier.fillParentMaxSize()) { retry() } }
                            }
                            loadState.mediator?.append is LoadState.Error -> {
                                val error = loadState.append as LoadState.Error
                                item { ErrorView(error.error.localizedMessage!!, modifier = Modifier.wrapContentHeight()) { retry() } }
                            }
                        }
                    }
                }
            }
        })
}

@Composable
fun ErrorView(
    errorMessage: String,
    modifier: Modifier,
) {

}

@Composable
fun CenterProgressIndicator() {
    Box(
        Modifier.fillMaxHeight().fillMaxWidth().padding(top = 250.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
