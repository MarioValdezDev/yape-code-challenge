package mx.mariovaldez.yapecodechallenge.home.presentation.mapper

import mx.mariovaldez.yapecodechallenge.domain.mapper.Mapper
import mx.mariovaldez.yapecodechallenge.home.data.models.Ingredient
import mx.mariovaldez.yapecodechallenge.home.presentation.models.IngredientUI
import javax.inject.Inject

internal class IngredientUIMapper @Inject constructor() : Mapper<Ingredient, IngredientUI> {
    override fun map(value: Ingredient): IngredientUI = with(value) {
        IngredientUI(
            text,
            weight,
            foodCategory,
            foodId,
            image
        )
    }
}
