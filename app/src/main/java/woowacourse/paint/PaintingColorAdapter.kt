package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PaintingColorAdapter(
    private val colors: List<Int>,
    private val setPaintingColor: (paintingColor: Int) -> Unit,
) : RecyclerView.Adapter<PaintingColorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaintingColorViewHolder {
        return PaintingColorViewHolder.from(parent, setPaintingColor)
    }

    override fun onBindViewHolder(holder: PaintingColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}
