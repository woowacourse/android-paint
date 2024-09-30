package woowacourse.paint.presentation

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorPaletteBinding

class ColorPaletteViewHolder(private val binding: ItemColorPaletteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(colorResId: Int, colorPaletteListener: ColorPaletteListener) {
        binding.colorResId = colorResId
        binding.colorPaletteListener = colorPaletteListener
    }
}
