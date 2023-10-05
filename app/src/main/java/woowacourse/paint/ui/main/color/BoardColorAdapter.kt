package woowacourse.paint.ui.main.color

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.model.BrushColorItem

class BoardColorAdapter(
    private val onColorClick: (BrushColorItem) -> Unit,
) : ListAdapter<BrushColorItem, BoardColorViewHolder>(boardColorDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardColorViewHolder {
        return BoardColorViewHolder.create(parent, onColorClick)
    }

    override fun onBindViewHolder(holder: BoardColorViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun changeColorList(colors: List<BrushColorItem>) {
        submitList(colors)
    }

    companion object {
        private val boardColorDiffUtil = object : DiffUtil.ItemCallback<BrushColorItem>() {
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
