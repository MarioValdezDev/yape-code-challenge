package mx.mariovaldez.yapecodechallenge.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.mariovaldez.yapecodechallenge.home.presentation.models.RecipeUI
import javax.inject.Inject

@HiltViewModel
internal class DetailsViewModel @Inject constructor() : ViewModel() {


    private val _state: MutableStateFlow<State?> = MutableStateFlow(null)
    val state: StateFlow<State?> get() = _state

    fun showData(recipe: RecipeUI?) {
        _state.value =
            if (recipe != null) State.ShowData(recipe)
            else State.Error
    }

    sealed class State {
        data class ShowData(val recipe: RecipeUI) : State()
        data object Error : State()
    }

    companion object {

        const val RECIPE_KEY = "key_recipe"
    }
}