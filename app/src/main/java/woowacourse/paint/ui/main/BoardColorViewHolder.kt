package woowacourse.paint.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.paint.databinding.ItemMainBoardColorBinding
import woowacourse.paint.model.BoardColorItem

class BoardColorViewHolder(
    private val binding: ItemMainBoardColorBinding,
    onColorClick: (BoardColorItem) -> Unit,
) : ViewHolder(binding.root) {
    init {
        binding.onColorClickListener = onColorClick
    }

    fun bind(boardColorItem: BoardColorItem) {
        binding.item = boardColorItem
    }

    companion object {
        fun create(parent: ViewGroup, onColorClick: (BoardColorItem) -> Unit): BoardColorViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMainBoardColorBinding.inflate(layoutInflater, parent, false)
            return BoardColorViewHolder(binding, onColorClick)
        }
    }
}
