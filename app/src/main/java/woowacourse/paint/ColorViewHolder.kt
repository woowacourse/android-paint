package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorBinding
import kotlin.properties.Delegates

class ColorViewHolder(
    parent: ViewGroup,
    onColorClicked: (Int) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false),
) {
    private val binding: ItemColorBinding = ItemColorBinding.bind(itemView)
    private var itemColor by Delegates.notNull<Int>()

    init {
        itemView.setOnClickListener {
            onColorClicked(itemColor)
        }
    }

    fun bind(item: Int) {
        itemColor = item
        binding.color = itemColor
    }
}
