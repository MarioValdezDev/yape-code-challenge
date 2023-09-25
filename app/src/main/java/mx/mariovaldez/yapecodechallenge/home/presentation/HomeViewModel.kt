package mx.mariovaldez.yapecodechallenge.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.yapecodechallenge.home.domain.usecase.GetRecipes
import mx.mariovaldez.yapecodechallenge.home.presentation.models.RecipeUI
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getRecipes: GetRecipes,
) : ViewModel() {


    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> get() = _state

    fun fetchData() {
        viewModelScope.launch {
            kotlin.runCatching {
                getRecipes()
            }
                .onSuccess {
                    //println(it)
                    _state.value = State.Success(it)
                }
                .onFailure {
                    println("error $it")
                    _state.value = State.Error
                }
        }
    }

    sealed class State {

        data object Loading : State()
        data class Success(val recipes: List<RecipeUI>) : State()
        data object Error : State()
    }
}
