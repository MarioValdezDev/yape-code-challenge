package mx.mariovaldez.yapecodechallenge.home.presentation.models

import java.io.Serializable

data class RecipeUI(

    val id: String,
    val label: String,
    val image: String,
    val description: String,
    val healthLabels: List<String>,
    val ingredientLines: List<String>,
    val ingredients: List<IngredientUI>,
    val calories: Number,
    val totalWeight: Number,
    val latitude: Number,
    val longitude: Number
) : Serializable

data class IngredientUI(
    val text: String,
    val weight: Number,
    val foodCategory: String?,
    val foodId: String,
    val image: String?
) : Serializable
