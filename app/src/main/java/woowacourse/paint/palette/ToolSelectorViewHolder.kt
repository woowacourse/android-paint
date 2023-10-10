package woowacourse.paint.palette

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.databinding.ToolBoxItemBinding

class ToolSelectorViewHolder(
    parent: ViewGroup,
    onItemClick: (Tool) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.tool_box_item, parent, false),
) {
    private val binding = ToolBoxItemBinding.bind(itemView)

    init {
        binding.toolClickListener = onItemClick
    }

    fun bind(item: Tool) {
        binding.tool = item
    }
}
