package woowacourse.paint.adapter

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.brush.BrushType
import woowacourse.paint.databinding.ItemBrushBinding

class BrushTypeViewHolder(
    private val binding: ItemBrushBinding,
    private val onClickBrush: (BrushType) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(brushType: BrushType) {
        binding.btnBrush.apply {
            setText(brushType.brushNameId)
            setOnClickListener {
                onClickBrush(brushType)
            }
        }
    }
}
