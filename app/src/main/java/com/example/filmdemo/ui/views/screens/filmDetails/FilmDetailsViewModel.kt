package com.example.filmdemo.ui.views.screens.filmDetails

import androidx.lifecycle.*
import com.example.filmdemo.data.model.entity.Film
import com.example.filmdemo.data.model.entity.People
import com.example.filmdemo.data.repository.PersonRepository
import com.example.filmdemo.ui.uiState.ViewStateDelegate
import com.example.filmdemo.ui.uiState.ViewStateDelegateImpl
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
        val film: Film? = null,
        var characters: List<People> = listOf(),
    )

    sealed interface Event {
        data class ShowError(val message: String) : Event
    }

    fun setFilm(film: Film?) {
        if(film != null) {
            viewModelScope.launch {
                val charactersFlow = personRepository.getAllMatching(film.characters).toList()
                reduce { state ->
                    state.copy(
                        film = film,
                        characters = charactersFlow
                    )
                }
            }
        }
    }

    fun onDetailItemSelected(item: String) {

    }
}