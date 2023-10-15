package woowacourse.paint.main.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
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
            onColorClickListener(adapterPosition)
        }
    }

    fun bind(paintColor: PaintColor) {
        binding.color = getColor(itemView.context, paintColor.colorRes)
    }
}
