package woowacourse.paint.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.model.BrushTools

class DrawingModeAdapter(
    private val brushTools: List<BrushTools>,
    private val onItemClick: (BrushTools) -> Unit
) : RecyclerView.Adapter<DrawingModeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawingModeViewHolder {
        return DrawingModeViewHolder.create(
            parent
        ) { position -> onItemClick(brushTools[position]) }
    }

    override fun getItemCount(): Int = brushTools.size

    override fun onBindViewHolder(holder: DrawingModeViewHolder, position: Int) {
        holder.bind(brushTools[position])
    }
}
