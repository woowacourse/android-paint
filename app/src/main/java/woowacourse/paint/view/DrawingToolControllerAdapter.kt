package woowacourse.paint.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemDrawingToolButtonBinding
import woowacourse.paint.model.DrawingTool
import woowacourse.paint.view.listener.DrawingToolChangeListener

class DrawingToolControllerAdapter(
    private val drawingTools: List<DrawingTool>,
    private val drawingToolChangeListener: DrawingToolChangeListener,
) : RecyclerView.Adapter<DrawingToolControllerAdapter.DrawingToolViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DrawingToolViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDrawingToolButtonBinding.inflate(layoutInflater, parent, false)
        return DrawingToolViewHolder(binding, drawingToolChangeListener)
    }

    override fun onBindViewHolder(
        holder: DrawingToolViewHolder,
        position: Int,
    ) {
        holder.bind(drawingTools[position])
    }

    override fun getItemCount(): Int = drawingTools.size

    class DrawingToolViewHolder(
        private val binding: ItemDrawingToolButtonBinding,
        drawingToolChangeListener: DrawingToolChangeListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.drawingToolChangeListener = drawingToolChangeListener
        }

        fun bind(drawingTool: DrawingTool) {
            binding.brush = drawingTool
        }
    }
}
