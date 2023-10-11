package woowacourse.paint.adapter.tool

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemToolsBinding
import woowacourse.paint.model.Tool

class ToolViewHolder(
    private val binding: ItemToolsBinding,
    private val onToolClick: (tool: Tool) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener { onToolClick(Tool.valueOf(adapterPosition)) }
    }

    fun bind(tool: Tool) {
        binding.tvToolsName.text = tool.toolName
    }

    companion object {

        fun from(
            parent: ViewGroup,
            onClick: (tool: Tool) -> Unit,
        ): ToolViewHolder = ToolViewHolder(
            ItemToolsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick,
        )
    }
}