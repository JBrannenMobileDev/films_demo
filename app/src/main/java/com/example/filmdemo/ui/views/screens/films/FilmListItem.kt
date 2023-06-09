package com.example.filmdemo.ui.views.screens.films

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.filmdemo.data.model.entity.Film
import com.example.filmdemo.ui.navigation.NavigationDestination
import kotlin.reflect.KFunction1

@Composable
fun FilmListItem (
    navController: NavController,
    film: Film?,
    onItemClicked: KFunction1<Film, Unit>
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor =  MaterialTheme.colorScheme.secondary,
        ),
        modifier = Modifier
        .padding(vertical = 8.dp, horizontal = 16.dp)
        .height(64.dp)
        .fillMaxWidth()
        .clickable {
            if (film != null) {
                onItemClicked(film)
                navController.navigate(NavigationDestination.FilmDetailsScreen.destination)
            }
        }
    ) {
        Box(
            Modifier.fillMaxWidth().fillMaxHeight().padding(start = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = "EPISODE ${film?.episodeId} - ${film?.title ?: "Title not available"}")
        }
    }
}