package mx.mariovaldez.yapecodechallenge.home.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.yapecodechallenge.databinding.ActivityHomeBinding
import mx.mariovaldez.yapecodechallenge.home.presentation.adapter.RecipesListAdapter
import mx.mariovaldez.yapecodechallenge.home.presentation.models.RecipeUI
import mx.mariovaldez.yapecodechallenge.ktx.exhaustive
import mx.mariovaldez.yapecodechallenge.ktx.gone
import mx.mariovaldez.yapecodechallenge.ktx.observe
import mx.mariovaldez.yapecodechallenge.ktx.viewBinding
import mx.mariovaldez.yapecodechallenge.ktx.visible

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by viewBinding(
        ActivityHomeBinding::inflate
    )

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupObservers()
        viewModel.fetchData()
    }

    private fun setupObservers() {
        viewModel.state.observe(this, ::handle)
    }

    private lateinit var recipesListAdapter: RecipesListAdapter

    private fun handle(state: HomeViewModel.State) {
        when (state) {
            HomeViewModel.State.Error -> {}
            HomeViewModel.State.Loading -> showProgress()
            is HomeViewModel.State.Success -> showData(state.recipes)
        }.exhaustive
    }

    private fun showData(recipes: List<RecipeUI>) {
        recipesListAdapter = RecipesListAdapter() {

        }.apply {
            addRecipes(recipes)
        }
        with(binding.productsRecyclerView) {
            adapter = recipesListAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)
        }
        binding.productsRecyclerView.visible()
        hideProgress()
    }

    private fun showProgress() {
        with(binding) {
            skeleton.root.startShimmer()
            skeleton.root.visible()
            productsRecyclerView.gone()
        }
    }

    private fun hideProgress() {
        with(binding) {
            skeleton.root.gone()
            skeleton.root.stopShimmer()
            productsRecyclerView.visible()

        }
    }

    companion object {

        fun launch(from: Context) =
            from.startActivity(Intent(from, HomeActivity::class.java))
    }
}
