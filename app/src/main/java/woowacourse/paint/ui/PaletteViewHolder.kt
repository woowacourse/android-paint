package woowacourse.paint.ui

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaletteColorBinding

class PaletteViewHolder(
    private val binding: ItemPaletteColorBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(colorRes: Int, paletteAction: PaletteAction) {
        val color = ContextCompat.getColor(binding.root.context, colorRes)
        binding.color = color
        binding.actionHandler = paletteAction
    }
}
