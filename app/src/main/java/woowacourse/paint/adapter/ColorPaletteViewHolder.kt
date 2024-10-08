package woowacourse.paint.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.brush.ColorPalette
import woowacourse.paint.databinding.ItemColorPaletteBinding

class ColorPaletteViewHolder(
    private val binding: ItemColorPaletteBinding,
    private val onClickColorPalette: (ColorPalette) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(colorPalette: ColorPalette) {
        binding.paletteButton.apply {
            setBackgroundColor(ContextCompat.getColor(context, colorPalette.colorRes))
            setOnClickListener {
                onClickColorPalette(colorPalette)
            }
        }
    }
}
