package woowacourse.paint

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaintColorBinding

class PaintColorViewHolder(private val binding: ItemPaintColorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        color: PaintColor,
        viewModel: PaintViewModel,
    ) {
        binding.color = color
        binding.vm = viewModel
    }
}
