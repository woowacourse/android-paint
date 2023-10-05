package woowacourse.paint

import androidx.recyclerview.widget.DiffUtil
import woowacourse.paint.model.PaintBrush

object BrushDiffUtilCallback : DiffUtil.ItemCallback<PaintBrush>() {

    override fun areItemsTheSame(oldItem: PaintBrush, newItem: PaintBrush): Boolean {
        return oldItem.brushTool == newItem.brushTool
    }

    override fun areContentsTheSame(oldItem: PaintBrush, newItem: PaintBrush): Boolean {
        return oldItem == newItem
    }
}
