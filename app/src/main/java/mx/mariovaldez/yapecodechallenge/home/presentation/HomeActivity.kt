package mx.mariovaldez.yapecodechallenge.home.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mx.mariovaldez.yapecodechallenge.databinding.ActivityHomeBinding
import mx.mariovaldez.yapecodechallenge.details.presentation.DetailsActivity
import mx.mariovaldez.yapecodechallenge.home.presentation.adapter.RecipesListAdapter
import mx.mariovaldez.yapecodechallenge.home.presentation.models.RecipeUI
import mx.mariovaldez.yapecodechallenge.ktx.exhaustive
import mx.mariovaldez.yapecodechallenge.ktx.gone
import mx.mariovaldez.yapecodechallenge.ktx.hideKeyboard
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

        setupViews()
        setupObservers()
        viewModel.fetchData()
    }

    private fun setupObservers() {
        viewModel.state.observe(this, ::handle)
    }

    private lateinit var recipesListAdapter: RecipesListAdapter

    private fun handle(state: HomeViewModel.State) {
        when (state) {
            HomeViewModel.State.Error -> {
                runBlocking {
                    delay(2000)
                    showError()
                }
            }

            HomeViewModel.State.Loading -> showProgress()
            is HomeViewModel.State.Success -> showData(state.recipes)
        }.exhaustive
    }

    private fun showError() {
        with(binding) {
            skeleton.root.gone()
            recipesRecyclerView.gone()
            errorLayout.errorContainer.visible()
        }
    }

    private fun showData(recipes: List<RecipeUI>) {
        recipesListAdapter = RecipesListAdapter { recipe ->
            hideKeyboard()
            navigateToDetails(recipe)
        }.apply {
            addRecipes(recipes)
        }
        with(binding.recipesRecyclerView) {
            adapter = recipesListAdapter
            setHasFixedSize(true)
        }
        binding.recipesRecyclerView.visible()
        hideProgress()
    }

    private fun navigateToDetails(recipe: RecipeUI) {
        DetailsActivity.launch(this, recipe)
    }

    private fun showProgress() {
        with(binding) {
            skeleton.root.startShimmer()
            skeleton.root.visible()
            recipesRecyclerView.gone()
            errorLayout.errorContainer.gone()
        }
    }

    private fun hideProgress() {
        with(binding) {
            skeleton.root.gone()
            skeleton.root.stopShimmer()
            recipesRecyclerView.visible()
            errorLayout.errorContainer.gone()
        }
    }

    private fun setupViews() {
        with(binding) {
            errorLayout.tryAgainButton.setOnClickListener {
                viewModel.fetchData()
            }
            searchTextInputEditText.addTextChangedListener {
                viewModel.filterRecipes(it.toString())
            }

            toolbar.searchButton.setOnClickListener {
                searchTextInputLayout.visible()
                toolbar.searchButton.gone()
            }
            searchTextInputLayout.setEndIconOnClickListener {
                hideKeyboard()
                searchTextInputEditText.setText("")
                searchTextInputLayout.gone()
                toolbar.searchButton.visible()
                hideKeyboard()
            }
        }
    }

    companion object {

        fun launch(from: Context) =
            from.startActivity(Intent(from, HomeActivity::class.java))
    }
}
