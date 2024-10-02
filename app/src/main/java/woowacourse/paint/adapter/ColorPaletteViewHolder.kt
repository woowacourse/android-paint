package woowacourse.paint.adapter

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.brush.ColorPalette
import woowacourse.paint.databinding.ItemColorPaletteBinding

class ColorPaletteViewHolder(
    private val binding: ItemColorPaletteBinding,
    private val onClickColorPalette: (ColorPalette) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(colorPalette: ColorPalette) {
        binding.paletteButton.apply {
            setBackgroundColor(colorPalette.color)
            setOnClickListener {
                onClickColorPalette(colorPalette)
            }
        }
    }
}
