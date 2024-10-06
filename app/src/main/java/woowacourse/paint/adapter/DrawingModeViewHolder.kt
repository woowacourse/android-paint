package woowacourse.paint.adapter

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.action.PaintActionHandler
import woowacourse.paint.databinding.ItemPaintDrawingModeBinding
import woowacourse.paint.uimodel.DrawingModeUiModel

class DrawingModeViewHolder(
    private val binding: ItemPaintDrawingModeBinding,
    private val actionHandler: PaintActionHandler,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(drawingModeUiModel: DrawingModeUiModel) {
        binding.drawingModeUiModel = drawingModeUiModel
        binding.actionHandler = actionHandler
    }
}
