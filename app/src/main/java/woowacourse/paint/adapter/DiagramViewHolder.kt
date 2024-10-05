package woowacourse.paint.adapter

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.Diagram
import woowacourse.paint.databinding.ItemDiagramBinding

class DiagramViewHolder(private val binding: ItemDiagramBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Diagram) {
        binding.item = item
        binding.btnDiagram.text = item.actionName
    }
}
