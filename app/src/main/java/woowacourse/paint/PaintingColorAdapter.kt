package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PaintingColorAdapter(
    private val paintingColors: List<PaintingColor>,
    private val setPaintingColor: (paintingColor: PaintingColor) -> Unit,
) : RecyclerView.Adapter<PaintingColorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaintingColorViewHolder {
        return PaintingColorViewHolder.from(parent, setPaintingColor)
    }

    override fun onBindViewHolder(holder: PaintingColorViewHolder, position: Int) {
        holder.bind(paintingColors[position])
    }

    override fun getItemCount(): Int {
        return paintingColors.size
    }
}
