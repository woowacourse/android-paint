package woowacourse.paint.adapter

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.action.PaintActionHandler
import woowacourse.paint.databinding.ItemPaintColorBinding
import woowacourse.paint.model.PaintColor
import woowacourse.paint.util.Color

class PaintColorViewHolder(private val binding: ItemPaintColorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        paintColor: PaintColor,
        actionHandler: PaintActionHandler,
    ) {
        binding.paintColor = paintColor
        binding.actionHandler = actionHandler
    }
}
