package woowacourse.paint.adapter.tool

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.model.Tool
import woowacourse.paint.model.Tool.CIRCLE
import woowacourse.paint.model.Tool.ERASER
import woowacourse.paint.model.Tool.PEN
import woowacourse.paint.model.Tool.RECTANGLE

class ToolAdapter(
    private val onToolsClick: (tool: Tool) -> Unit,
) : RecyclerView.Adapter<ToolViewHolder>() {

    private val tools: List<Tool> = listOf(PEN, RECTANGLE, CIRCLE, ERASER)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolViewHolder =
        ToolViewHolder.from(parent, onToolsClick)

    override fun getItemCount(): Int = tools.size

    override fun onBindViewHolder(holder: ToolViewHolder, position: Int) {
        holder.bind(tools[position])
    }
}

