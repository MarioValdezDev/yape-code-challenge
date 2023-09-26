package mx.mariovaldez.yapecodechallenge.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.yapecodechallenge.home.domain.usecase.FilterRecipes
import mx.mariovaldez.yapecodechallenge.home.domain.usecase.GetRecipes
import mx.mariovaldez.yapecodechallenge.home.presentation.models.RecipeUI
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getRecipes: GetRecipes,
    private val filterRecipes: FilterRecipes,
) : ViewModel() {


    private lateinit var recipes: List<RecipeUI>
    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> get() = _state

    fun fetchData() {
        viewModelScope.launch {
            kotlin.runCatching {
                getRecipes()
            }
                .onSuccess {
                    //println(it)
                    recipes = it
                    _state.value = State.Success(it)
                }
                .onFailure {
                    println("error $it")
                    _state.value = State.Error
                }
        }
    }

    fun filterRecipes(newText: String?) {
        _state.value = State.Success(
            if (!newText.isNullOrBlank()) filterRecipes(newText, recipes)
            else recipes
        )
    }

    sealed class State {

        data object Loading : State()
        data class Success(val recipes: List<RecipeUI>) : State()
        data object Error : State()
    }
}
