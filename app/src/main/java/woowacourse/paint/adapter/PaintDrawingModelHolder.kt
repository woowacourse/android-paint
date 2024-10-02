package woowacourse.paint.adapter

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.action.PaintActionHandler
import woowacourse.paint.databinding.ItemPaintDrawingModeBinding
import woowacourse.paint.uimodel.DrawingModeUiModel

class PaintDrawingModelHolder(private val binding: ItemPaintDrawingModeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        drawingModeUiModel: DrawingModeUiModel,
        actionHandler: PaintActionHandler,
    ) {
        binding.drawingModeUiModel = drawingModeUiModel
        binding.actionHandler = actionHandler
    }
}
