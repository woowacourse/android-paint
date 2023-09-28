package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class PaintColorPaletteAdapter(
    private val onColorChanged: (Int) -> Unit,
) : ListAdapter<PaintColor, PaintColorPaletteViewHolder>(PaintColorDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaintColorPaletteViewHolder {
        return PaintColorPaletteViewHolder.create(parent, onColorChanged)
    }

    override fun onBindViewHolder(holder: PaintColorPaletteViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun updateColors(colors: List<PaintColor>) {
        submitList(colors)
    }

    companion object {
        private val PaintColorDiffUtil = object : DiffUtil.ItemCallback<PaintColor>() {
            override fun areItemsTheSame(
                oldItem: PaintColor,
                newItem: PaintColor,
            ): Boolean {
                return oldItem.color == newItem.color
            }

            override fun areContentsTheSame(
                oldItem: PaintColor,
                newItem: PaintColor,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
