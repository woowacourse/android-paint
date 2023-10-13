package woowacourse.paint.palette

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.databinding.ItemPaletteBinding

class PaletteViewHolder(
    parent: ViewGroup,
    onPaletteClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_palette, parent, false),
) {
    private val binding: ItemPaletteBinding = ItemPaletteBinding.bind(itemView)

    init {
        itemView.setOnClickListener {
            onPaletteClick(binding.huePalette.hue)
        }
    }

    fun bind(@ColorRes hue: Int) {
        binding.hue = binding.root.context.getColor(hue)
    }
}
