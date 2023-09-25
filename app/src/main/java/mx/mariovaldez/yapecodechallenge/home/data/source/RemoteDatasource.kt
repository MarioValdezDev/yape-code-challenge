package mx.mariovaldez.yapecodechallenge.home.data.source

import mx.mariovaldez.yapecodechallenge.data.remote.services.ApiServices
import mx.mariovaldez.yapecodechallenge.home.data.models.Recipe
import javax.inject.Inject

internal class RemoteDatasource @Inject constructor(
    private val apiServices: ApiServices,
) {

    suspend fun getRecipes(): List<Recipe> = apiServices.getRecipes()
}
