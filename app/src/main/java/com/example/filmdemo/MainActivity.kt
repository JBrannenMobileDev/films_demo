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
import com.example.filmdemo.ui.views.FilmsViewModel
import com.example.filmdemo.ui.views.drivers.DriverDetailsScreen
import com.example.filmdemo.ui.views.drivers.DriverListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val driversViewModel = hiltViewModel<FilmsViewModel>()

            FilmDemoTheme {
                NavHost(navController = navController, startDestination = NavigationDestination.DriversScreen.destination) {

                    composable(NavigationDestination.DriversScreen.destination) {
                        val uiState by driversViewModel.collectWithLifecycle()
                        DriverListScreen(
                            navController = navController,
                            assignments = uiState.films,
                            totalSS = uiState.totalSS,
                            onItemClicked = driversViewModel::itemClicked
                        )
                    }

                    composable(NavigationDestination.DriverDetailsScreen.destination) {
                        val uiState by driversViewModel.collectWithLifecycle()
                        DriverDetailsScreen(navController = navController, assignment = uiState.selectedFilm)
                    }
                }
            }
        }
    }
}