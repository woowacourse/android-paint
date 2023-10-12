package woowacourse.paint.adapter

import androidx.recyclerview.widget.DiffUtil
import woowacourse.paint.model.BrushBox

object BrushDiffUtilCallback : DiffUtil.ItemCallback<BrushBox>() {

    override fun areItemsTheSame(oldItem: BrushBox, newItem: BrushBox): Boolean {
        return oldItem.brushTool == newItem.brushTool
    }

    override fun areContentsTheSame(oldItem: BrushBox, newItem: BrushBox): Boolean {
        return oldItem == newItem
    }
}
