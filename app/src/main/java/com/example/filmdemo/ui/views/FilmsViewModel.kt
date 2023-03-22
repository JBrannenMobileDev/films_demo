package com.example.filmdemo.ui.views

import androidx.lifecycle.ViewModel
import com.example.filmdemo.data.model.entity.Film
import androidx.lifecycle.viewModelScope
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
) : ViewModel(), ViewStateDelegate<FilmsViewModel.UiState, FilmsViewModel.Event> by ViewStateDelegateImpl(UiState()) {

    data class UiState(
        val selectedFilm: Film? = null,
        val films: Flow<PagingData<Film>>? = null,
    )

    sealed interface Event {
        object NotUsedEvent : Event  //Sample event. If we needed any events they would go here
    }

    init {
        viewModelScope.launch {
            val filmsFlow = filmRepository.getAllPaged()
            reduce { state ->
                state.copy(
                    films = filmsFlow
                )
            }
        }
    }

    fun itemClicked(item: Film){
        viewModelScope.launch {
            reduce { state ->
                state.copy(
                    selectedFilm = item
                )
            }
        }
    }
}