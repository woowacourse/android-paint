package woowacourse.paint.adapter

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.action.PaintActionHandler
import woowacourse.paint.databinding.ItemPaintColorBinding
import woowacourse.paint.util.Color

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
