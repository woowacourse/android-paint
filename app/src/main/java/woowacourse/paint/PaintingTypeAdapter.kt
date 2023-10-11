package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.painting.PaintingType

class PaintingTypeAdapter(
    private val paintingTypes: List<PaintingType> = PaintingType.values().toList(),
    private val setPaintingType: (paintingType: PaintingType) -> Unit,
) : RecyclerView.Adapter<PaintingTypeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaintingTypeViewHolder {
        return PaintingTypeViewHolder.from(parent, setPaintingType)
    }

    override fun onBindViewHolder(holder: PaintingTypeViewHolder, position: Int) {
        holder.bind(paintingTypes[position])
    }

    override fun getItemCount(): Int {
        return paintingTypes.size
    }
}
