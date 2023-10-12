package woowacourse.paint.presentation.ui.main.colors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorBinding

class ColorItemViewHolder(
    private val binding: ItemColorBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemColor) {
        binding.itemColor = item
    }

    companion object {
        fun of(parent: ViewGroup): ColorItemViewHolder {
            val binding = ItemColorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
            return ColorItemViewHolder(binding)
        }
    }
}
