package woowacourse.paint.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToolAdapter(private val tools: List<Int>, private val onToolClicked: (Int) -> Unit) :
    RecyclerView.Adapter<ToolViewHolder>() {
    private var selectedToolIdx: Int = DEFAULT_TOOL_IDX

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolViewHolder {
        return ToolViewHolder(parent, onToolClicked, selectedToolIdx)
    }

    override fun getItemCount(): Int = tools.size

    override fun onBindViewHolder(holder: ToolViewHolder, position: Int) {
        holder.bind()
    }

    fun restoreSelectedTool(idx: Int) {
        selectedToolIdx = idx
    }

    companion object {
        private const val DEFAULT_TOOL_IDX = 0
    }
}
