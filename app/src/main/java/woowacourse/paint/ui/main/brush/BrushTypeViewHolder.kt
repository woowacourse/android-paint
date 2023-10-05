package woowacourse.paint.ui.main.brush

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
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
    }

    companion object {
        fun create(parent: ViewGroup, onTypeClick: (BrushTypeItem) -> Unit): BrushTypeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMainBrushTypeBinding.inflate(layoutInflater, parent, false)
            return BrushTypeViewHolder(binding, onTypeClick)
        }
    }
}
