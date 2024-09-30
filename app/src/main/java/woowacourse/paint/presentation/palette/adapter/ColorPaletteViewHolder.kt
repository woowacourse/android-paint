package woowacourse.paint.presentation.palette.adapter

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorPaletteBinding
import woowacourse.paint.presentation.palette.ColorPaletteListener

class ColorPaletteViewHolder(private val binding: ItemColorPaletteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(colorResId: Int, colorPaletteListener: ColorPaletteListener) {
        binding.colorResId = colorResId
        binding.colorPaletteListener = colorPaletteListener
    }
}
