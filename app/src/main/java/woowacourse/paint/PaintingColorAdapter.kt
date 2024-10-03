package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.model.PaintingColor

class PaintingColorAdapter(
    private val paintingPaintingColors: List<PaintingColor>,
    private val setPaintingColor: (paintingColor: PaintingColor) -> Unit,
) : RecyclerView.Adapter<PaintingColorViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PaintingColorViewHolder = PaintingColorViewHolder.from(parent, setPaintingColor)

    override fun onBindViewHolder(
        holder: PaintingColorViewHolder,
        position: Int,
    ) {
        holder.bind(paintingPaintingColors[position])
    }

    override fun getItemCount(): Int = paintingPaintingColors.size
}
