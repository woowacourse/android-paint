package woowacourse.paint.adapter

import androidx.recyclerview.widget.DiffUtil
import woowacourse.paint.model.ColorBox

object ColorDiffUtilCallback : DiffUtil.ItemCallback<ColorBox>() {
    override fun areItemsTheSame(oldItem: ColorBox, newItem: ColorBox): Boolean {
        return oldItem.color == newItem.color
    }

    override fun areContentsTheSame(oldItem: ColorBox, newItem: ColorBox): Boolean {
        return oldItem == newItem
    }
}
