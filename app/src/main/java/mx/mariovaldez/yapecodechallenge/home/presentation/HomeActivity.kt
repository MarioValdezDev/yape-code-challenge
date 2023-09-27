package mx.mariovaldez.yapecodechallenge.home.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.yapecodechallenge.R
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
            HomeViewModel.State.Error -> {}
            HomeViewModel.State.Loading -> showProgress()
            is HomeViewModel.State.Success -> showData(state.recipes)
        }.exhaustive
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
        }
    }

    private fun hideProgress() {
        with(binding) {
            skeleton.root.gone()
            skeleton.root.stopShimmer()
            recipesRecyclerView.visible()
        }
    }

    private fun setupViews() {
        supportActionBar?.title = "Home"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.actionSearch)
        val searchView: SearchView? = searchItem.actionView as SearchView?

        searchView?.isIconified = false
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterRecipes(newText)
                return true
            }
        })

        return true
    }

    companion object {

        fun launch(from: Context) =
            from.startActivity(Intent(from, HomeActivity::class.java))
    }
}
