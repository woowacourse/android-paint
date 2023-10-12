package woowacourse.paint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.databinding.ItemToolBinding

class ToolViewHolder(private val parent: ViewGroup, private val onToolClicked: (Int) -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_tool, parent, false),
    ) {

    private val binding = ItemToolBinding.bind(itemView)

    init {
        setupDefaultTool()
        setupOnToolClick()
    }

    private fun setupDefaultTool() {
        val initialView = parent.getChildAt(DEFAULT_TOOL_IDX)
        initialView?.isSelected = true
    }

    private fun setupOnToolClick() {
        itemView.setOnClickListener { currentView ->
            parent.forEach { it.isSelected = false }
            onToolClicked(adapterPosition)
            currentView.isSelected = true
        }
    }

    fun bind() {
        binding.tvTool.text = parent.resources.getStringArray(R.array.toolNames)[adapterPosition]
    }

    companion object {
        private const val DEFAULT_TOOL_IDX = 0
    }
}
