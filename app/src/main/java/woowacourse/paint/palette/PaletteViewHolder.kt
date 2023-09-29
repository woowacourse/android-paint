package woowacourse.paint.palette

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaletteBinding

class PaletteViewHolder(private val binding: ItemPaletteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(position: Int, color: Int, onPaletteClick: (Int) -> Unit) {
        binding.palette.apply {
            setBackgroundColor(color)
            setOnClickListener {
                onPaletteClick(position)
            }
        }
    }
}
