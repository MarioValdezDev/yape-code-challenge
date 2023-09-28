package mx.mariovaldez.yapecodechallenge

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal class ServiceTest {

    private lateinit var apiServices: TestApiService

    @Before
    internal fun setup() {
        apiServices = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TestApiService::class.java)
    }

    @Test
    fun get_recipes_is_not_null() = runBlocking {
        val recipes = apiServices.getRecipes()
        assertNotNull(recipes.body())
    }

    @Test
    fun verify_recipes_response_is_200() {
        val recipes = runBlocking { apiServices.getRecipes() }
        Assert.assertEquals(recipes.isSuccessful, true)
    }
}
