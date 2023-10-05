package woowacourse.paint.ui.main.color

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.model.BoardColorItem

class BoardColorAdapter(
    private val onColorClick: (BoardColorItem) -> Unit,
) : ListAdapter<BoardColorItem, BoardColorViewHolder>(boardColorDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardColorViewHolder {
        return BoardColorViewHolder.create(parent, onColorClick)
    }

    override fun onBindViewHolder(holder: BoardColorViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun changeColorList(colors: List<BoardColorItem>) {
        submitList(colors)
    }

    companion object {
        private val boardColorDiffUtil = object : DiffUtil.ItemCallback<BoardColorItem>() {
            override fun areItemsTheSame(
                oldItem: BoardColorItem,
                newItem: BoardColorItem,
            ): Boolean {
                return oldItem.color == newItem.color
            }

            override fun areContentsTheSame(
                oldItem: BoardColorItem,
                newItem: BoardColorItem,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
