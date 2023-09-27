package woowacourse.paint.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.model.BoardColor

class BoardColorAdapter(
    private val onColorClick: (BoardColor) -> Unit,
) : ListAdapter<BoardColor, BoardColorViewHolder>(ColorDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardColorViewHolder {
        return BoardColorViewHolder.create(parent, onColorClick)
    }

    override fun onBindViewHolder(holder: BoardColorViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun changeColorList(colors: List<BoardColor>) {
        submitList(colors)
    }

    companion object {
        private val ColorDiffUtil = object : DiffUtil.ItemCallback<BoardColor>() {
            override fun areItemsTheSame(
                oldItem: BoardColor,
                newItem: BoardColor,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: BoardColor,
                newItem: BoardColor,
            ): Boolean {
                return oldItem.colorInt == newItem.colorInt
            }
        }
    }
}
