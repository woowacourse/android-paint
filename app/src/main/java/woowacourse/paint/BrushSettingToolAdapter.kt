package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class BrushSettingToolAdapter(
    private val onBrushChanged: (Brush) -> Unit,
) : ListAdapter<BrushModel, BrushSettingToolViewHolder>(BrushDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushSettingToolViewHolder {
        return BrushSettingToolViewHolder.create(parent, onBrushChanged)
    }

    override fun onBindViewHolder(holder: BrushSettingToolViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun updateBrushes(brushes: List<BrushModel>) {
        submitList(brushes)
    }

    companion object {
        private val BrushDiffUtil = object : DiffUtil.ItemCallback<BrushModel>() {
            override fun areItemsTheSame(
                oldItem: BrushModel,
                newItem: BrushModel,
            ): Boolean {
                return oldItem.brush == newItem.brush
            }

            override fun areContentsTheSame(
                oldItem: BrushModel,
                newItem: BrushModel,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
