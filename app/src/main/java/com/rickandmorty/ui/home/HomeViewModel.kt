package com.rickandmorty.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmorty.data.Result
import com.rickandmorty.domain.use_case.GetCharacterUseCase
import com.rickandmorty.domain.use_case.getCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase : getCharactersUseCase
): ViewModel(){

    var state by mutableStateOf(HomeState(isLoading = true))
        private set

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentPage = 1

    init{
        getCharacters(increase = false)
    }

    private fun getCharacters(increase: Boolean) {
        viewModelScope.launch {
            if(increase) currentPage++ else if (currentPage > 1) currentPage--
            val showPrevious = currentPage > 1
            val showNext = currentPage < 42
            getCharactersUseCase(currentPage).onEach { result ->
                when(result) {
                    is Result.Success -> {
                        state = state.copy(
                            characters = result.data ?: emptyList(),
                            isLoading = false,
                            showPreviews = showPrevious
                        )
                    }
                    is Result.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(
                            result.message ?: "Unkown error"
                        ))
                    }
                    is Result.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }


    sealed class UIEvent{
        data class ShowSnackBar(val message : String): UIEvent()
    }
}