package woowacourse.paint

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaintColorBinding

class PaintColorViewHolder(private val binding: ItemPaintColorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        color: Color,
        isChecked: Boolean,
        actionHandler: PaintActionHandler,
    ) {
        binding.color = color
        binding.isChecked = isChecked
        binding.actionHandler = actionHandler
    }
}
