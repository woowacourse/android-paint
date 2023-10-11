package woowacourse.paint.ui.glocanvas

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.ui.model.PaintColorModel

class PaintColorPaletteAdapter(
    private val onItemClick: (Int) -> Unit,
) : ListAdapter<PaintColorModel, PaintColorPaletteViewHolder>(PaintColorDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaintColorPaletteViewHolder {
        return PaintColorPaletteViewHolder.create(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: PaintColorPaletteViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun updateColors(colors: List<PaintColorModel>) {
        submitList(colors)
    }

    companion object {
        private val PaintColorDiffUtil = object : DiffUtil.ItemCallback<PaintColorModel>() {
            override fun areItemsTheSame(
                oldItem: PaintColorModel,
                newItem: PaintColorModel,
            ): Boolean {
                return oldItem.color == newItem.color
            }

            override fun areContentsTheSame(
                oldItem: PaintColorModel,
                newItem: PaintColorModel,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
