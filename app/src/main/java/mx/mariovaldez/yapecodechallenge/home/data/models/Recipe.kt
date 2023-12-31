package mx.mariovaldez.yapecodechallenge.home.data.models

data class Recipe(

    val uri: String,
    val label: String,
    val image: String,
    val description: String,
    val healthLabels: List<String>,
    val ingredientLines: List<String>,
    val ingredients: List<Ingredient>,
    val calories: Number,
    val totalWeight: Number,
    val latitude: Number,
    val longitude: Number
)

data class Ingredient(

    val text: String,
    val weight: Number,
    val foodCategory: String,
    val foodId: String,
    val image: String?
)
