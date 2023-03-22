package com.example.filmdemo.ui.views.screens.films

import androidx.lifecycle.*
import com.example.filmdemo.data.model.entity.Film
import androidx.paging.PagingData
import com.example.filmdemo.data.repository.FilmRepository
import com.example.filmdemo.ui.uiState.ViewStateDelegate
import com.example.filmdemo.ui.uiState.ViewStateDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val filmRepository: FilmRepository,
) : ViewModel(), ViewStateDelegate<FilmsViewModel.UiState, FilmsViewModel.Event> by ViewStateDelegateImpl(
    UiState()
) {

    data class UiState(
        val selectedFilm: Film? = null,
        val films: Flow<PagingData<Film>>? = null,
    )

    sealed interface Event {
        data class ShowError(val message: String) : Event
    }

    init {
        loadFilms()
    }

    private fun loadFilms() {
        viewModelScope.launch {
            val filmsFlow = filmRepository.getAllPaged()
            reduce { state ->
                state.copy(
                    films = filmsFlow
                )
            }
        }
    }

    fun onRefreshSelected(){
        loadFilms()
    }

    fun onErrorLoading(message: String) {
        viewModelScope.launch {
            sendEvent(Event.ShowError(message))
        }
    }

    fun onFilmSelected(item: Film) {
        viewModelScope.launch {
            reduce { state ->
                state.copy(
                    selectedFilm = item
                )
            }
        }
    }

    fun onDetailItemSelected(item: String) {

    }
}