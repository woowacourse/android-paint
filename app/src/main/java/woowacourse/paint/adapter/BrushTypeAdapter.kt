package woowacourse.paint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.brush.BrushType
import woowacourse.paint.databinding.ItemBrushBinding

class BrushTypeAdapter(
    private val brushTypes: List<BrushType>,
    private val onClickBrush: (BrushType) -> Unit,
) : RecyclerView.Adapter<BrushTypeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushTypeViewHolder {
        val binding =
            ItemBrushBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrushTypeViewHolder(binding, onClickBrush)
    }

    override fun getItemCount(): Int = brushTypes.size

    override fun onBindViewHolder(
        holder: BrushTypeViewHolder,
        position: Int,
    ) {
        holder.bind(brushTypes[position])
    }
}
