package woowacourse.paint.ui.glocanvas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaintColorBinding
import woowacourse.paint.ui.model.PaintColorModel

class PaintColorPaletteViewHolder private constructor(
    private val binding: ItemPaintColorBinding,
    onItemClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.onItemClick = onItemClick
    }

    fun bind(paintColor: PaintColorModel) {
        binding.paintColor = paintColor
    }

    companion object {
        fun create(parent: ViewGroup, onItemClick: (Int) -> Unit): PaintColorPaletteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPaintColorBinding.inflate(layoutInflater, parent, false)
            return PaintColorPaletteViewHolder(binding, onItemClick)
        }
    }
}
