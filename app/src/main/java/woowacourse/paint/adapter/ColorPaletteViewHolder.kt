package woowacourse.paint.adapter

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.paint.ColorPaletteHandler
import woowacourse.paint.databinding.ItemColorPaletteBinding

class ColorPaletteViewHolder(
    private val binding: ItemColorPaletteBinding,
    colorPaletteHandler: ColorPaletteHandler,
) : ViewHolder(binding.root) {
    init {
        binding.handler = colorPaletteHandler
    }

    fun bind(
        @ColorRes colorId: Int,
    ) {
        binding.color = ContextCompat.getColor(binding.root.context, colorId)
    }
}
