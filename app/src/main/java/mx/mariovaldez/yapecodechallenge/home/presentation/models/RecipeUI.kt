package mx.mariovaldez.yapecodechallenge.home.presentation.models

data class RecipeUI(
    val id: String,
    val label: String,
    val image: String,
    val healthLabels: List<String>,
    val ingredientLines: List<String>,
    val ingredients: List<IngredientUI>,
    val calories: Number,
    val totalWeight: Number,
   // val locale: String,
)

data class IngredientUI(
    val text: String,
    val weight: Number,
    val foodCategory: String?,
    val foodId: String,
    val image: String?
)