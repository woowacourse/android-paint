package woowacourse.paint.palette

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToolSelectorAdapter(private val onItemClick: (Tool) -> Unit) :
    RecyclerView.Adapter<ToolSelectorViewHolder>() {

    private val tools = Tool.values()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolSelectorViewHolder =
        ToolSelectorViewHolder(parent, onItemClick)

    override fun getItemCount(): Int = tools.size

    override fun onBindViewHolder(holder: ToolSelectorViewHolder, position: Int) {
        holder.bind(tools[position])
    }
}
