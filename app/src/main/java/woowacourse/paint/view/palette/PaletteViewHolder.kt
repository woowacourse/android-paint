package woowacourse.paint.view.palette

import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ViewPaletteBinding

class PaletteViewHolder(private val binding: ViewPaletteBinding, onClickPalette: (Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.paletteImageButton.setOnClickListener {
            onClickPalette(adapterPosition)
        }
    }

    fun bind(color: Int) {
        DrawableCompat.setTint(binding.paletteImageButton.drawable.mutate(), color)
    }
}
