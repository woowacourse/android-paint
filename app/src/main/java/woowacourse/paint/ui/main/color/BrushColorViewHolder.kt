package woowacourse.paint.ui.main.color

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.paint.databinding.ItemMainBrushColorBinding
import woowacourse.paint.model.BrushColorItem

class BrushColorViewHolder(
    private val binding: ItemMainBrushColorBinding,
    onColorClick: (BrushColorItem) -> Unit,
) : ViewHolder(binding.root) {
    init {
        binding.onColorClickListener = onColorClick
    }

    fun bind(brushColorItem: BrushColorItem) {
        binding.item = brushColorItem
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onColorClick: (BrushColorItem) -> Unit,
        ): BrushColorViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMainBrushColorBinding.inflate(layoutInflater, parent, false)
            return BrushColorViewHolder(binding, onColorClick)
        }
    }
}
