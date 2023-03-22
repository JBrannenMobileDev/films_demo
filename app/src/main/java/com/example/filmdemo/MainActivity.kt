package com.example.filmdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filmdemo.ui.navigation.NavigationDestination
import com.example.filmdemo.ui.theme.FilmDemoTheme
import com.example.filmdemo.ui.uiState.collectWithLifecycle
import com.example.filmdemo.ui.views.FilmDetailsScreen
import com.example.filmdemo.ui.views.FilmListScreen
import com.example.filmdemo.ui.views.FilmsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val filmViewModel = hiltViewModel<FilmsViewModel>()

            FilmDemoTheme {
                NavHost(navController = navController, startDestination = NavigationDestination.FilmsScreen.destination) {

                    composable(NavigationDestination.FilmsScreen.destination) {
                        val uiState by filmViewModel.collectWithLifecycle()
                        FilmListScreen(
                            filmsSource = uiState.films,
                            navController = navController,
                            onItemClicked = filmViewModel::itemClicked
                        )
                    }

                    composable(NavigationDestination.FilmDetailsScreen.destination) {
                        val uiState by filmViewModel.collectWithLifecycle()
                        FilmDetailsScreen(navController = navController, film = uiState.selectedFilm)
                    }
                }
            }
        }
    }
}