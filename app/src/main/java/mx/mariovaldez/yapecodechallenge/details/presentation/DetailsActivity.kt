package mx.mariovaldez.yapecodechallenge.details.presentation

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.yapecodechallenge.R
import mx.mariovaldez.yapecodechallenge.databinding.ActivityDetailsBinding
import mx.mariovaldez.yapecodechallenge.details.presentation.DetailsViewModel.Companion.RECIPE_KEY
import mx.mariovaldez.yapecodechallenge.details.presentation.adapters.LabelListAdapter
import mx.mariovaldez.yapecodechallenge.home.presentation.models.RecipeUI
import mx.mariovaldez.yapecodechallenge.ktx.viewBinding

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val binding: ActivityDetailsBinding by viewBinding(
        ActivityDetailsBinding::inflate
    )
    private lateinit var healthLabels: LabelListAdapter
    private lateinit var ingredientsLabels: LabelListAdapter

    private val viewModel: DetailsViewModel by viewModels() // this is going to be used soon

    private lateinit var recipeUI: RecipeUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        recipeUI = intent.extras?.get(RECIPE_KEY) as RecipeUI
        setupViews()
        setupListeners()
        showData(recipeUI)
    }

    private fun setupListeners() {
        with(binding) {
            showMapButton.setOnClickListener {
                showDialog()
            }
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_map)
        dialog.show()

        val mMapView: MapView = dialog.findViewById<View>(R.id.map) as MapView
        MapsInitializer.initialize(this)
        mMapView.onCreate(dialog.onSaveInstanceState())
        mMapView.onResume()
        mMapView.getMapAsync {
            val sydney = LatLng(recipeUI.latitude.toDouble(), recipeUI.longitude.toDouble())
            it.addMarker(
                MarkerOptions()
                    .position(sydney)
                    .title("Marker in Sydney")
            )
            it.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
    }

    private fun setupViews() {
        supportActionBar?.title = "Recipe Detail"
    }

    private fun showData(recipe: RecipeUI) {
        ingredientsLabels = LabelListAdapter().apply {
            addLabels(recipe.ingredientLines)
        }
        healthLabels = LabelListAdapter().apply {
            addLabels(recipe.healthLabels)
        }
        with(binding) {
            descriptionLabelTextView.text = recipe.description
            labelTextView.text = recipe.label
            recipeImageView.load(recipe.image)
            healthLabelsRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = healthLabels
                setHasFixedSize(true)
            }
            ingredientsRecyclerView.apply {
                adapter = ingredientsLabels
                setHasFixedSize(true)
            }
        }
    }

    companion object {

        fun launch(from: Context, recipe: RecipeUI) = from.startActivity(
            Intent(
                from,
                DetailsActivity::class.java
            ).apply {
                putExtra(RECIPE_KEY, recipe)
            }
        )
    }
}
