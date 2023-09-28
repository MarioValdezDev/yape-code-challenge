package mx.mariovaldez.yapecodechallenge.home.data.repository

import mx.mariovaldez.yapecodechallenge.home.data.source.RemoteDatasource
import mx.mariovaldez.yapecodechallenge.home.presentation.mapper.RecipeUIMapper
import mx.mariovaldez.yapecodechallenge.home.presentation.models.RecipeUI
import javax.inject.Inject

internal class HomeRepository @Inject constructor(
    private val remoteDatasource: RemoteDatasource,
    private val recipeUIMapper: RecipeUIMapper
) {

    suspend fun getRecipes(): List<RecipeUI> =
        recipeUIMapper.map(remoteDatasource.getRecipes())
}
