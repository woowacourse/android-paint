package woowacourse.paint.palette

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.databinding.ColorBoxItemBinding

class ColorSelectorViewHolder(
    parent: ViewGroup,
    onItemClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.color_box_item, parent, false),
) {
    private val binding = ColorBoxItemBinding.bind(itemView)

    init {
        binding.colorClickListener = onItemClick
    }

    fun bind(item: Color) {
        binding.color = item
    }
}
