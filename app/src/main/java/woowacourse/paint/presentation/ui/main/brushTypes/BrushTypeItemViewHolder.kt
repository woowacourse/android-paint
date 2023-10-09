package woowacourse.paint.presentation.ui.main.brushTypes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemBrushTypeBinding

class BrushTypeItemViewHolder(
    private val binding: ItemBrushTypeBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemBrushType) {
        binding.itemBrushType = item
    }

    companion object {
        fun of(parent: ViewGroup): BrushTypeItemViewHolder {
            val binding = ItemBrushTypeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
            return BrushTypeItemViewHolder(binding)
        }
    }
}
