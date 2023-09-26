package mx.mariovaldez.yapecodechallenge.home.presentation.mapper

import mx.mariovaldez.yapecodechallenge.domain.mapper.Mapper
import mx.mariovaldez.yapecodechallenge.home.data.models.Recipe
import mx.mariovaldez.yapecodechallenge.home.presentation.models.RecipeUI
import mx.mariovaldez.yapecodechallenge.ktx.getId
import javax.inject.Inject

internal class RecipeUIMapper @Inject constructor(
    private val ingredientUIMapper: IngredientUIMapper,
) : Mapper<Recipe, RecipeUI> {
    override fun map(value: Recipe): RecipeUI = with(value) {
        RecipeUI(
            id = uri.getId(),
            label = label,
            image = image,
            healthLabels = healthLabels,
            ingredientLines = ingredientLines,
            ingredients = ingredientUIMapper.map(ingredients),
            calories = calories,
            totalWeight = totalWeight,
            description = description,
            longitude = longitude,
            latitude = latitude
        )
    }
}
