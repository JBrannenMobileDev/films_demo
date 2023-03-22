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
import com.example.filmdemo.data.model.entity.People
import com.example.filmdemo.ui.views.commonComposables.CenterProgressIndicator
import kotlin.reflect.KFunction1

@Composable
fun CharactersCategoryLazyRow(
    title: String,
    characters: List<People>,
    onPeopleItemSelected: KFunction1<People, Unit>,
) {
    Box(Modifier.height(175.dp)) {
        Box(Modifier.padding(start = 24.dp)) {
            Text(text = title, color = Color.White)
        }
        CharactersHorizontalList(
            characters = characters,
            onPeopleItemSelected = onPeopleItemSelected
        )
    }
}

@Composable
fun CharactersHorizontalList(
    characters: List<People>,
    onPeopleItemSelected: KFunction1<People, Unit>,
) {
    if(characters.isNotEmpty()) {
        LazyRow(
            Modifier.fillMaxWidth().padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(characters.size) { index ->
                CharactersHorizontalListItem(
                    character = characters[index],
                    onPeopleItemSelected = onPeopleItemSelected
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
fun CharactersHorizontalListItem(
    character: People,
    onPeopleItemSelected: KFunction1<People, Unit>,
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
        onClick = { onPeopleItemSelected(character) },
    ){
        Box(
            Modifier.fillMaxSize().padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = character.name,
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 3
            )
        }
    }
}