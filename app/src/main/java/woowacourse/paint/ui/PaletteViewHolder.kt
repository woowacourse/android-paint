package woowacourse.paint.ui

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaletteColorBinding

class PaletteViewHolder(
    private val binding: ItemPaletteColorBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        colorRes: Int,
        paletteAction: PaletteAction,
    ) {
        binding.color = colorRes
        binding.actionHandler = paletteAction
    }
}
