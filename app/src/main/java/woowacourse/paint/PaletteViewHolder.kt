package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaletteBinding

class PaletteViewHolder(
    parent: ViewGroup,
    onClick: (position: Int) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_palette, parent, false),
) {
    private val binding = ItemPaletteBinding.bind(itemView)

    init {
        binding.root.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun bind(@ColorInt color: Int) {
        binding.viewPalette.setBackgroundColor(color)
    }
}
