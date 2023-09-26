package mx.mariovaldez.yapecodechallenge.home.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import mx.mariovaldez.yapecodechallenge.R
import mx.mariovaldez.yapecodechallenge.databinding.ItemRecipeBinding
import mx.mariovaldez.yapecodechallenge.home.presentation.models.RecipeUI

internal class RecipesListAdapter(
    private val listener: (recipe: RecipeUI) -> Unit,
) : RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    private val recipes = mutableListOf<RecipeUI>()

    inner class ViewHolder(
        private val binding: ItemRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeUI) {
            with(binding) {

                imageView.load(recipe.image) {
                    crossfade(750)
                    placeholder(R.drawable.icv_recipe_icon)
                    scale(Scale.FILL)
                }
                recipeNameTextView.text = recipe.label
                binding.root.setOnClickListener {
                    listener(recipe)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun addRecipes(recipes: List<RecipeUI>) {
        this.recipes.clear()
        this.recipes.addAll(recipes)
        notifyDataSetChanged()
    }
}
