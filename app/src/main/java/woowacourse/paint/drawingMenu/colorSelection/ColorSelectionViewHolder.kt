package woowacourse.paint.drawingMenu.colorSelection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.ColorPalette
import woowacourse.paint.databinding.ItemColorSelectionBinding

class ColorSelectionViewHolder(
    private val binding: ItemColorSelectionBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        colorPalette: ColorPalette,
        onClickColorListener: (ColorPalette) -> Unit,
    ) {
        binding.itemColor.background =
            ContextCompat.getDrawable(binding.root.context, colorPalette.color)
        binding.itemColor.setOnClickListener { onClickColorListener(colorPalette) }
    }

    companion object {

        fun from(
            parent: ViewGroup,
        ): ColorSelectionViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemColorSelectionBinding.inflate(layoutInflater, parent, false)
            return ColorSelectionViewHolder(binding)
        }
    }
}
