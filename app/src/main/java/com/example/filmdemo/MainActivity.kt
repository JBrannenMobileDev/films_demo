package com.example.filmdemo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filmdemo.ui.navigation.NavigationDestination
import com.example.filmdemo.ui.theme.FilmDemoTheme
import com.example.filmdemo.ui.uiState.collectEvent
import com.example.filmdemo.ui.uiState.collectWithLifecycle
import com.example.filmdemo.ui.views.screens.filmDetails.FilmDetailsScreen
import com.example.filmdemo.ui.views.screens.filmDetails.FilmDetailsViewModel
import com.example.filmdemo.ui.views.screens.films.FilmListScreen
import com.example.filmdemo.ui.views.screens.films.FilmsViewModel
import com.example.filmdemo.ui.views.screens.films.FilmsViewModel.Event.ShowFilmLoadError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private fun errorToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val filmViewModel = hiltViewModel<FilmsViewModel>()
            val lifecycle: LifecycleOwner = LocalLifecycleOwner.current

            FilmDemoTheme {
                NavHost(navController = navController, startDestination = NavigationDestination.FilmsScreen.destination) {

                    composable(NavigationDestination.FilmsScreen.destination,) {
                        val uiState by filmViewModel.collectWithLifecycle()
                        val mContext = LocalContext.current

                        LaunchedEffect(key1 = Unit) {
                            filmViewModel.collectEvent(lifecycle) { event ->
                                when (event) {
                                    is ShowFilmLoadError -> {
                                        errorToast(mContext, event.message)
                                    }
                                }
                            }
                        }

                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painterResource(id = R.drawable.starwars_bg),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize(),
                            )
                            FilmListScreen(
                                filmsSource = uiState.films,
                                navController = navController,
                                onItemClicked = filmViewModel::onFilmSelected,
                                onErrorLoading = filmViewModel::onErrorLoading,
                                onRefreshSelected = filmViewModel::onRefreshSelected,
                            )
                        }
                    }

                    composable(NavigationDestination.FilmDetailsScreen.destination) {
                        val detailsViewModel = hiltViewModel<FilmDetailsViewModel>()
                        val filmsUiState by filmViewModel.collectWithLifecycle()
                        val detailsUiState by detailsViewModel.collectWithLifecycle()
                        detailsViewModel.fetchDetails(filmsUiState.selectedFilm)

                        val mContext = LocalContext.current

                        LaunchedEffect(key1 = Unit) {
                            detailsViewModel.collectEvent(lifecycle) { event ->
                                when (event) {
                                    is FilmDetailsViewModel.Event.ShowCharacterLoadError -> {
                                        errorToast(mContext, event.message)
                                    }
                                }
                            }
                        }

                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painterResource(id = R.drawable.vadar),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize(),
                            )
                            FilmDetailsScreen(
                                navController = navController,
                                film = filmsUiState.selectedFilm,
                                onPeopleItemSelected = detailsViewModel::onPeopleItemSelected,
                                onStarshipItemSelected = detailsViewModel::onStarshipItemSelected,
                                characters = detailsUiState.characters,
                                starships = detailsUiState.starships,
                            )
                        }
                    }
                }
            }
        }
    }
}