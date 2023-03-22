package com.example.filmdemo.ui.navigation

sealed class NavigationDestination(open val destination: String) {
    object FilmsScreen : NavigationDestination("films_screen")
    object FilmDetailsScreen : NavigationDestination("film_details_screen")
}