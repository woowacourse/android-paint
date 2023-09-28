package woowacourse.paint.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.databinding.ItemColorBinding
import woowacourse.paint.model.PaintColor

class ColorViewHolder(
    parent: ViewGroup,
    onColorClickListener: (Int) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.item_color,
        parent,
        false,
    ),
) {
    private val binding = ItemColorBinding.bind(itemView)

    init {
        binding.root.setOnClickListener {
            @ColorInt val color = getColor(itemView.context, PaintColor.getColorRes(adapterPosition))
            onColorClickListener(color)
        }
    }

    fun bind(paintColor: PaintColor) {
        @ColorInt val color = getColor(itemView.context, paintColor.colorRes)
        binding.root.setBackgroundColor(color)
    }
}
