package woowacourse.paint.presentation.ui.main.brushTypes

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BrushTypesAdapter(
    private val itemBrushTypes: List<ItemBrushType>,
) : RecyclerView.Adapter<BrushTypeItemViewHolder>() {

    override fun getItemCount(): Int = itemBrushTypes.size

    override fun onBindViewHolder(holder: BrushTypeItemViewHolder, position: Int) {
        holder.bind(itemBrushTypes[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushTypeItemViewHolder {
        return BrushTypeItemViewHolder.of(parent)
    }
}
