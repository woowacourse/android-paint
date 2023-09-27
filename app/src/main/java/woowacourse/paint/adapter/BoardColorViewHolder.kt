package woowacourse.paint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.model.PaintColor
import woowacourse.paint.databinding.ItemMainBoardColorBinding

class BoardColorViewHolder(
    private val binding: ItemMainBoardColorBinding,
    onColorClick: (PaintColor) -> Unit,
) : ViewHolder(binding.root) {
    init {
        binding.onColorClickListener = onColorClick
    }

    fun bind(color: PaintColor) {
        binding.color = color
    }

    companion object {
        fun create(parent: ViewGroup, onColorClick: (PaintColor) -> Unit): BoardColorViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMainBoardColorBinding.inflate(layoutInflater, parent, false)
            return BoardColorViewHolder(binding, onColorClick)
        }
    }
}
