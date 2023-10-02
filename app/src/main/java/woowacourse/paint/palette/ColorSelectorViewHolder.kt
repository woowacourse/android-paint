package woowacourse.paint.palette

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.databinding.ColorBoxItemBinding

class ColorSelectorViewHolder(
    parent: ViewGroup,
    private val onItemClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.color_box_item, parent, false),
) {
    private val binding = ColorBoxItemBinding.bind(itemView)

    fun bind(item: Color) {
        binding.color = item
        binding.colorClickListener = onItemClick
    }
}
