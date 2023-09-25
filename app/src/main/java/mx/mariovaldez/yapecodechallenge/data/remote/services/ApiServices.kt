package mx.mariovaldez.yapecodechallenge.data.remote.services

import mx.mariovaldez.yapecodechallenge.home.data.models.Recipe
import retrofit2.http.GET

internal interface ApiServices {

    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>
}
