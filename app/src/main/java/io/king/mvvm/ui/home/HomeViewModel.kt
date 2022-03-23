package io.king.mvvm.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.king.mvvm.data.repository.CharacterRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import io.king.mvvm.data.api.model.Character

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterRepo: CharacterRepo
): ViewModel(){

    private val _state = MutableStateFlow(emptyList<Character>())
    val state:StateFlow<List<Character>>
    get() =_state


    init {
        viewModelScope.launch {
            val character = characterRepo.getCharacters()
            _state.value = character
        }
    }
}