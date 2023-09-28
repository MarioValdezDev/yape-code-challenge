package mx.mariovaldez.yapecodechallenge

import mx.mariovaldez.yapecodechallenge.home.data.models.Recipe
import retrofit2.Response
import retrofit2.http.GET

interface TestApiService {

    @GET("recipes")
    suspend fun getRecipes(): Response<List<Recipe>>
}
