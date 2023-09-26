package woowacourse.paint.palette

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ViewPaletteBinding

class PaletteViewHolder(val binding: ViewPaletteBinding, onClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.paletteImageButton.setOnClickListener {
            onClick(adapterPosition)
        }
    }
}
