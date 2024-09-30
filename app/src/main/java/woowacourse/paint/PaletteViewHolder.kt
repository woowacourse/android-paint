package woowacourse.paint

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorBinding

class PaletteViewHolder(
    private val binding: ItemColorBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(color: PaletteColor) {
        val targetColor = ContextCompat.getColor(binding.root.context, color.colorRes)
        binding.vwColor.backgroundTintList = ColorStateList.valueOf(targetColor)
    }
}
