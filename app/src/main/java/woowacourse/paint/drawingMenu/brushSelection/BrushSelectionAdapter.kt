package woowacourse.paint.drawingMenu.brushSelection

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.model.brush.BrushType

class BrushSelectionAdapter(private val onClickColorListener: (BrushType) -> Unit) :
    RecyclerView.Adapter<BrushSelectionViewHolder>() {

    private val brushTypes: List<BrushType> = BrushType.values().toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushSelectionViewHolder {
        return BrushSelectionViewHolder.from(parent)
    }

    override fun getItemCount(): Int = brushTypes.size

    override fun onBindViewHolder(holder: BrushSelectionViewHolder, position: Int) {
        holder.bind(brushTypes[position], onClickColorListener)
    }
}
