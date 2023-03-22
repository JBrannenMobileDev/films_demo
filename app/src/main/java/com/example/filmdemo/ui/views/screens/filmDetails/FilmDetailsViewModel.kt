package com.example.filmdemo.ui.views.screens.filmDetails

import androidx.lifecycle.*
import com.example.filmdemo.data.model.entity.Film
import com.example.filmdemo.data.model.entity.People
import com.example.filmdemo.data.repository.PersonRepository
import com.example.filmdemo.ui.uiState.ViewStateDelegate
import com.example.filmdemo.ui.uiState.ViewStateDelegateImpl
import com.example.filmdemo.ui.views.screens.films.FilmsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    private val personRepository: PersonRepository,
) : ViewModel(), ViewStateDelegate<FilmDetailsViewModel.UiState, FilmDetailsViewModel.Event> by ViewStateDelegateImpl(
    UiState()
) {

    data class UiState(
        var characters: List<People> = listOf(),
    )

    sealed interface Event {
        data class ShowCharacterLoadError(val message: String) : Event
    }

    fun fetchCharacters(film: Film?) {
        if(film != null) {
            viewModelScope.launch {
                val characters = personRepository.getAllMatching(film.characters)?.toList()
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
        }
    }

    fun onDetailItemSelected(item: String) {

    }
}