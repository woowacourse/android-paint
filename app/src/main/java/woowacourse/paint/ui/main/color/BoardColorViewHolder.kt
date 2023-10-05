package woowacourse.paint.ui.main.color

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.paint.databinding.ItemMainBoardColorBinding
import woowacourse.paint.model.BrushColorItem

class BoardColorViewHolder(
    private val binding: ItemMainBoardColorBinding,
    onColorClick: (BrushColorItem) -> Unit,
) : ViewHolder(binding.root) {
    init {
        binding.onColorClickListener = onColorClick
    }

    fun bind(brushColorItem: BrushColorItem) {
        binding.item = brushColorItem
    }

    companion object {
        fun create(parent: ViewGroup, onColorClick: (BrushColorItem) -> Unit): BoardColorViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMainBoardColorBinding.inflate(layoutInflater, parent, false)
            return BoardColorViewHolder(binding, onColorClick)
        }
    }
}
