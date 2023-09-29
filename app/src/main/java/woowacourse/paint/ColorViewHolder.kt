package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorPaletteBinding

class ColorViewHolder(private val binding: ItemColorPaletteBinding, onItemClick: (ColorUiModel) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.onItemClick = onItemClick
    }

    fun bind(item: ColorUiModel) {
        binding.colorModel = item
    }

    companion object {
        fun create(parent: ViewGroup, onItemClick: (ColorUiModel) -> Unit): ColorViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemColorPaletteBinding.inflate(layoutInflater, parent, false)
            return ColorViewHolder(binding, onItemClick)
        }
    }
}
