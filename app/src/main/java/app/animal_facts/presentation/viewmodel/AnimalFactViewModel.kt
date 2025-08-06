package app.animal_facts.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.animal_facts.domain.usecase.GetAnimalFactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalFactViewModel @Inject constructor(
    private val getAnimalFactUseCase: GetAnimalFactUseCase
) : ViewModel() {

    var fact by mutableStateOf("Loading...")
        private set

    init {
        fetchAnimalFact()
    }

    fun fetchAnimalFact() {
        viewModelScope.launch {
            try {
                val animalFact = getAnimalFactUseCase()
                fact = animalFact.fact
            } catch (e: Exception) {
                fact = "Error: ${e.message}"
            }
        }
    }

}