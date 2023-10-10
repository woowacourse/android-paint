package woowacourse.paint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.databinding.ItemColorBinding

class ColorViewHolder(
    parent: ViewGroup,
    onColorClicked: (Int) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false),
) {
    private val binding: ItemColorBinding = ItemColorBinding.bind(itemView)

    init {
        itemView.setOnClickListener {
            onColorClicked(adapterPosition)
        }
    }

    fun bind(item: Int) {
        binding.color = item
    }
}
