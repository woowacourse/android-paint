package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaintColorBinding

class PaintColorPaletteViewHolder private constructor(
    private val binding: ItemPaintColorBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(color: Int) {
        binding.color = color
    }

    companion object {
        fun create(parent: ViewGroup): PaintColorPaletteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPaintColorBinding.inflate(layoutInflater, parent, false)
            return PaintColorPaletteViewHolder(binding)
        }
    }
}
