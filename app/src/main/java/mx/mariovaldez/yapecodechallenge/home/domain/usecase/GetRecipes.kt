package mx.mariovaldez.yapecodechallenge.home.domain.usecase

import kotlinx.coroutines.withContext
import mx.mariovaldez.melicodechallenge.domain.dispatchers.DefaultDispatcherProvider
import mx.mariovaldez.yapecodechallenge.home.data.repository.HomeRepository
import mx.mariovaldez.yapecodechallenge.home.presentation.models.RecipeUI
import javax.inject.Inject

internal class GetRecipes @Inject constructor(
    private val repository: HomeRepository,
    private val defaultDispatcherProvider: DefaultDispatcherProvider
) {

    suspend operator fun invoke(): List<RecipeUI> = withContext(defaultDispatcherProvider.default) {
        repository.getRecipes()
    }
}
