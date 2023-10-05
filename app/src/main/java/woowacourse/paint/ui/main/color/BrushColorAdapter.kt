package woowacourse.paint.ui.main.color

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.model.BrushColorItem

class BrushColorAdapter(
    private val onColorClick: (BrushColorItem) -> Unit,
) : ListAdapter<BrushColorItem, BrushColorViewHolder>(brushColorDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushColorViewHolder {
        return BrushColorViewHolder.create(parent, onColorClick)
    }

    override fun onBindViewHolder(holder: BrushColorViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun changeColorList(colors: List<BrushColorItem>) {
        submitList(colors)
    }

    companion object {
        private val brushColorDiffUtil = object : DiffUtil.ItemCallback<BrushColorItem>() {
            override fun areItemsTheSame(
                oldItem: BrushColorItem,
                newItem: BrushColorItem,
            ): Boolean {
                return oldItem.color == newItem.color
            }

            override fun areContentsTheSame(
                oldItem: BrushColorItem,
                newItem: BrushColorItem,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
