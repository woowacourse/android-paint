package woowacourse.paint.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.PaletteItemBinding

class PaletteViewHolder private constructor(
    private val binding: PaletteItemBinding,
    onItemClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.paletteItemView.setOnClickListener { onItemClick(adapterPosition) }
    }

    fun bind(@ColorInt color: Int) {
        binding.color = color
    }

    companion object {
        fun create(
            parent: ViewGroup,
            itemSize: Int,
            onItemClick: (Int) -> Unit,
        ): PaletteViewHolder {
            val binding =
                PaletteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            binding.root.layoutParams.width = parent.measuredWidth / itemSize
            return PaletteViewHolder(binding, onItemClick)
        }
    }
}
