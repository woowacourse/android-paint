package woowacourse.paint.ui.main.brush

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.paint.R
import woowacourse.paint.customView.content.BrushType
import woowacourse.paint.databinding.ItemMainBrushTypeBinding
import woowacourse.paint.model.BrushTypeItem

class BrushTypeViewHolder(
    private val binding: ItemMainBrushTypeBinding,
    onColorClick: (BrushTypeItem) -> Unit,
) : ViewHolder(binding.root) {
    init {
        binding.onTypeClickListener = onColorClick
    }

    fun bind(brushTypeItem: BrushTypeItem) {
        binding.item = brushTypeItem
        binding.tvBrushType.text = when (brushTypeItem.brushType) {
            BrushType.Stroke -> binding.root.context.getString(R.string.brush_type_stroke)
            BrushType.Rectangle -> binding.root.context.getString(R.string.brush_type_rectangle)
            BrushType.Eraser -> binding.root.context.getString(R.string.brush_type_eraser)
        }
    }

    companion object {
        fun create(parent: ViewGroup, onTypeClick: (BrushTypeItem) -> Unit): BrushTypeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMainBrushTypeBinding.inflate(layoutInflater, parent, false)
            return BrushTypeViewHolder(binding, onTypeClick)
        }
    }
}
