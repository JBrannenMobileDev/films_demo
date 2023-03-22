package com.example.filmdemo.ui.views

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
fun DetailsCategoryLazyRow(
    title: String,
    characters: List<People>,
    onDetailItemSelected: KFunction1<String, Unit>,
) {
    Box(Modifier.height(175.dp)) {
        Box(Modifier.padding(start = 24.dp)) {
            Text(text = title, color = Color.White)
        }
        HorizontalList(characters = characters, onDetailItemSelected = onDetailItemSelected)
    }
}

@Composable
fun HorizontalList(
    characters: List<People>,
    onDetailItemSelected: KFunction1<String, Unit>,
) {
    if(characters.isNotEmpty()) {
        LazyRow(
            Modifier.fillMaxWidth().padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(characters.size) { index ->
                HorizontalListItem(title = characters[index].name, itemUrl = characters[index].url, onDetailItemSelected = onDetailItemSelected)
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
fun HorizontalListItem(
    title: String,
    itemUrl: String,
    onDetailItemSelected: KFunction1<String, Unit>,
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
        onClick = { onDetailItemSelected(itemUrl) },
    ){
        Box(
            Modifier.fillMaxSize().padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = title,
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 3
            )
        }
    }
}