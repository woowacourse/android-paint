package woowacourse.paint

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.paint.databinding.ItemColorPaletteBinding

class ColorPaletteViewHolder(
    private val binding: ItemColorPaletteBinding,
) : ViewHolder(binding.root) {
    fun bind(colorId: Int) {
        val color = ContextCompat.getColor(binding.root.context, colorId)
        binding.color = color
    }
}
