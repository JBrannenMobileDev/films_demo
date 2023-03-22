package com.example.filmdemo.ui.views.screens.filmDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.filmdemo.data.model.entity.Starship
import com.example.filmdemo.ui.views.commonComposables.CenterProgressIndicator
import kotlin.reflect.KFunction1

@Composable
fun StarshipsCategoryLazyRow(
    title: String,
    starships: List<Starship>,
    onStarshipItemSelected: KFunction1<Starship, Unit>,
) {
    Box(Modifier.height(175.dp)) {
        Box(Modifier.padding(start = 24.dp)) {
            Text(text = title, color = Color.White)
        }
        StarshipsHorizontalList(
            starships = starships,
            onStarshipItemSelected = onStarshipItemSelected
        )
    }
}

@Composable
fun StarshipsHorizontalList(
    starships: List<Starship>,
    onStarshipItemSelected: KFunction1<Starship, Unit>,
) {
    if(starships.isNotEmpty()) {
        LazyRow(
            Modifier.fillMaxWidth().padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(starships.size) { index ->
                StarshipsHorizontalListItem(
                    starship = starships[index],
                    onStarshipItemSelected = onStarshipItemSelected
                )
            }
        }
    } else {
        Box(Modifier.height(200.dp).fillMaxWidth()) {
            CenterProgressIndicator(Modifier.height(125.dp).fillMaxWidth())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarshipsHorizontalListItem(
    starship: Starship,
    onStarshipItemSelected: KFunction1<Starship, Unit>,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor =  MaterialTheme.colorScheme.tertiary,
        ),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .width(175.dp)
            .height(125.dp)
            .padding(8.dp),
        onClick = { onStarshipItemSelected(starship) },
    ){
        Box(
            Modifier.fillMaxSize().padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = starship.name,
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 3
            )
        }
    }
}