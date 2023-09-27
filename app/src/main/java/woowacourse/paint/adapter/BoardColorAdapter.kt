package woowacourse.paint.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.model.PaintColor

class BoardColorAdapter(
    private val onColorClick: (PaintColor) -> Unit,
) : ListAdapter<PaintColor, BoardColorViewHolder>(ColorDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardColorViewHolder {
        return BoardColorViewHolder.create(parent, onColorClick)
    }

    override fun onBindViewHolder(holder: BoardColorViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun changeColorList(colors: List<PaintColor>) {
        submitList(colors)
    }

    companion object {
        private val ColorDiffUtil = object : DiffUtil.ItemCallback<PaintColor>() {
            override fun areItemsTheSame(
                oldItem: PaintColor,
                newItem: PaintColor,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PaintColor,
                newItem: PaintColor,
            ): Boolean {
                return oldItem.colorRes == newItem.colorRes
            }
        }
    }
}
