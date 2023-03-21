package com.example.filmdemo.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.filmdemo.data.model.entity.Film
import com.example.filmdemo.ui.navigation.NavigationDestination

@Composable
fun DriverListItem (
    navController: NavController,
    film: Film,
    onItemClicked: (item: Film) -> Unit
) {
    Card(
        modifier = Modifier
        .padding(8.dp)
        .height(64.dp)
        .fillMaxWidth()
        .clickable {
            onItemClicked(film)
            navController.navigate(NavigationDestination.DriverDetailsScreen.destination)
        }
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = film.title)
        }
    }
}