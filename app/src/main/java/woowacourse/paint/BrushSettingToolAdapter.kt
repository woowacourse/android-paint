package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class BrushSettingToolAdapter : ListAdapter<Brush, BrushSettingToolViewHolder>(BrushDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushSettingToolViewHolder {
        return BrushSettingToolViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BrushSettingToolViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun updateBrushes(brushes: List<Brush>) {
        submitList(brushes)
    }

    companion object {
        private val BrushDiffUtil = object : DiffUtil.ItemCallback<Brush>() {
            override fun areItemsTheSame(
                oldItem: Brush,
                newItem: Brush,
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: Brush,
                newItem: Brush,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
