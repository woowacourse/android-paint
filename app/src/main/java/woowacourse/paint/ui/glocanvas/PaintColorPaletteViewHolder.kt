package woowacourse.paint.ui.glocanvas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaintColorBinding
import woowacourse.paint.ui.model.PaintColorModel

class PaintColorPaletteViewHolder private constructor(
    private val binding: ItemPaintColorBinding,
    onColorChanged: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.vPaintColor.setOnClickListener {
            binding.paintColor?.let {
                onColorChanged(it.color)
            }
        }
    }

    fun bind(paintColor: PaintColorModel) {
        binding.paintColor = paintColor
    }

    companion object {
        fun create(parent: ViewGroup, onColorChanged: (Int) -> Unit): PaintColorPaletteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPaintColorBinding.inflate(layoutInflater, parent, false)
            return PaintColorPaletteViewHolder(binding, onColorChanged)
        }
    }
}
