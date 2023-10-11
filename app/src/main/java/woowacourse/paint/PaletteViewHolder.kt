package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaletteBinding
import woowacourse.paint.model.palettecolor.PaletteColor

class PaletteViewHolder(
    parent: ViewGroup,
    private val onClick: (color: PaletteColor) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_palette, parent, false),
) {
    private val binding = ItemPaletteBinding.bind(itemView)

    private var color: PaletteColor = PaletteColor.RED

    init {
        binding.root.setOnClickListener {
            onClick(color)
        }
    }

    fun bind(color: PaletteColor) {
        this.color = color
        binding.viewPalette.setBackgroundColor(itemView.context.getColor(color.resourceId))
    }
}
