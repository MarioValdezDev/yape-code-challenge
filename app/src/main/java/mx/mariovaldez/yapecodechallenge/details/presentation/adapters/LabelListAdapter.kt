package mx.mariovaldez.yapecodechallenge.details.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.mariovaldez.yapecodechallenge.databinding.ItemLabelsBinding
import javax.inject.Inject

internal class LabelListAdapter @Inject constructor() :
    RecyclerView.Adapter<LabelListAdapter.ViewHolder>() {

    private val labels = mutableListOf<String>()

    inner class ViewHolder(
        private val binding: ItemLabelsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(label: String) {
            with(binding) {
                labelTextView.text = label
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemLabelsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = labels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(labels[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun addLabels(labels: List<String>) {
        this.labels.clear()
        this.labels.addAll(labels)
        notifyDataSetChanged()
    }
}
