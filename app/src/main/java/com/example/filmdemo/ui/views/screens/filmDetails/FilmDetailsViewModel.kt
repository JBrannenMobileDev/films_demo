package com.example.filmdemo.ui.views.screens.filmDetails

import androidx.lifecycle.*
import com.example.filmdemo.data.model.entity.Film
import com.example.filmdemo.data.model.entity.People
import com.example.filmdemo.data.model.entity.Starship
import com.example.filmdemo.data.repository.CharactersRepository
import com.example.filmdemo.data.repository.StarshipsRepository
import com.example.filmdemo.ui.uiState.ViewStateDelegate
import com.example.filmdemo.ui.uiState.ViewStateDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val starshipsRepository: StarshipsRepository,
) : ViewModel(), ViewStateDelegate<FilmDetailsViewModel.UiState, FilmDetailsViewModel.Event> by ViewStateDelegateImpl(
    UiState()
) {

    data class UiState(
        var characters: List<People> = listOf(),
        var starships: List<Starship> = listOf(),
    )

    sealed interface Event {
        data class ShowCharacterLoadError(val message: String) : Event
    }

    fun fetchDetails(film: Film?) {
        if(film != null) {
            viewModelScope.launch {
                val characters = charactersRepository.getAllMatching(film.characters)?.toList()
                if(characters != null) {
                    reduce { state ->
                        state.copy(
                            characters = characters
                        )
                    }
                } else {
                    viewModelScope.launch {
                        sendEvent(Event.ShowCharacterLoadError("The force is weak! Check your internet connection"))
                    }
                }
            }
            viewModelScope.launch {
                val starships = starshipsRepository.getAllMatching(film.starships)?.toList()
                if(starships != null) {
                    reduce { state ->
                        state.copy(
                            starships = starships
                        )
                    }
                } else {
                    viewModelScope.launch {
                        sendEvent(Event.ShowCharacterLoadError("The force is weak! Check your internet connection"))
                    }
                }
            }
        }
    }

    fun onPeopleItemSelected(item: People) {

    }

    fun onStarshipItemSelected(item: Starship) {

    }
}