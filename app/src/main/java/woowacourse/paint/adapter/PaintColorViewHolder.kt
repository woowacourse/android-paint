package woowacourse.paint.adapter

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.action.PaintActionHandler
import woowacourse.paint.databinding.ItemPaintColorBinding
import woowacourse.paint.uimodel.PaintColorUiModel

class PaintColorViewHolder(
    private val binding: ItemPaintColorBinding,
    private val actionHandler: PaintActionHandler,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(paintColorUiModel: PaintColorUiModel) {
        binding.paintColorUiModel = paintColorUiModel
        binding.actionHandler = actionHandler
    }
}
