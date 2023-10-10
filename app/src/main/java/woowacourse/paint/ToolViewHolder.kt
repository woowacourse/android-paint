package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemToolBinding
import woowacourse.paint.tool.Tools

class ToolViewHolder(private val parent: ViewGroup, onToolClicked: (Int) -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_tool, parent, false),
    ) {

    private val binding = ItemToolBinding.bind(itemView)

    init {
        itemView.setOnClickListener {
            onToolClicked(adapterPosition)
        }
    }

    fun bind(tools: Tools) {
        binding.tvTool.text = parent.resources.getStringArray(R.array.toolNames)[adapterPosition]
    }
}
