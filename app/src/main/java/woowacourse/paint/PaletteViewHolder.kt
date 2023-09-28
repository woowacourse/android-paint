package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
            onPaletteClick(binding.paintPalette.color)
        }
    }

    fun bind(color: Int) {
        binding.color = color
    }
}
