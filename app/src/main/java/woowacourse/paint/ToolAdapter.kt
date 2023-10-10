package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.tool.Tools

class ToolAdapter(private val tools: Array<Tools>, private val onToolClicked: (Int) -> Unit) :
    RecyclerView.Adapter<ToolViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolViewHolder {
        return ToolViewHolder(parent, onToolClicked)
    }

    override fun getItemCount(): Int = tools.size

    override fun onBindViewHolder(holder: ToolViewHolder, position: Int) {
        holder.bind(tools[position])
    }
}
