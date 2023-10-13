package woowacourse.paint.ui.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PaintAdapter(
    private val colors: IntArray,
    private val onColorClick: (color: Int) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PaintViewHolder.from(parent, onColorClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PaintViewHolder -> holder.bind(colors[position])
        }
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}
