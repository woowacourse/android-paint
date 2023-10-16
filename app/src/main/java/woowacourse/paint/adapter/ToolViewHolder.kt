package woowacourse.paint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.databinding.ItemToolBinding

class ToolViewHolder(
    private val parent: ViewGroup,
    private val onToolClicked: (Int) -> Unit,
    toolIdx: Int,
) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_tool, parent, false),
    ) {

    private val binding = ItemToolBinding.bind(itemView)

    init {
        setupOnToolClick()
        setupDefaultTool(toolIdx)
    }

    private fun setupDefaultTool(idx: Int) {
        val initialView = parent.getChildAt(idx)
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
}
