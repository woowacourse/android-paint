package woowacourse.paint.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.PaintColor
import woowacourse.paint.databinding.ItemColorButtonBinding

class ColorViewHolder(private val binding: ItemColorButtonBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PaintColor) {
        val color = ContextCompat.getColor(binding.root.context, item.res)
        binding.item = item
        binding.ivColor.setBackgroundColor(color)
    }
}
