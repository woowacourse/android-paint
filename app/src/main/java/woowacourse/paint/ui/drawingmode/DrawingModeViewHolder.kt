package woowacourse.paint.ui.drawingmode

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemDrawingModeBinding
import woowacourse.paint.model.DrawingMode

class DrawingModeViewHolder(
    private val binding: ItemDrawingModeBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(mode: DrawingMode) {
        binding.drawingMode = mode
    }
}
