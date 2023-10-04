package woowacourse.paint.customView.colorSelection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.ColorPalette
import woowacourse.paint.databinding.ItemColorSelectionBinding

class ColorSelectionViewHolder(
    private val binding: ItemColorSelectionBinding,
    private val clickListener: (ColorPalette) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(colorPalette: ColorPalette) {
        binding.itemColor.background =
            ContextCompat.getDrawable(binding.root.context, colorPalette.color)
        binding.itemColor.setOnClickListener { clickListener(colorPalette) }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            clickListener: (ColorPalette) -> Unit,
        ): ColorSelectionViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemColorSelectionBinding.inflate(layoutInflater, parent, false)
            return ColorSelectionViewHolder(binding, clickListener)
        }
    }
}
