package mx.mariovaldez.yapecodechallenge.home.domain.usecase

import mx.mariovaldez.yapecodechallenge.home.presentation.models.RecipeUI
import javax.inject.Inject

internal class FilterRecipes @Inject constructor() {

    operator fun invoke(word: String, recipes: List<RecipeUI>) = recipes.filter {
        it.label.lowercase().contains(word)
    }
}
