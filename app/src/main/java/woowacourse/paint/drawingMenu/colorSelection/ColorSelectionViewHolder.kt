package woowacourse.paint.drawingMenu.colorSelection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemOptionSelectionBinding
import woowacourse.paint.model.ColorPalette

class ColorSelectionViewHolder(
    private val binding: ItemOptionSelectionBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        colorPalette: ColorPalette,
        onClickColorListener: (ColorPalette) -> Unit,
    ) {
        binding.optionItem.background =
            ContextCompat.getDrawable(binding.root.context, colorPalette.color)
        binding.optionItem.setOnClickListener { onClickColorListener(colorPalette) }
    }

    companion object {

        fun from(
            parent: ViewGroup,
        ): ColorSelectionViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemOptionSelectionBinding.inflate(layoutInflater, parent, false)
            return ColorSelectionViewHolder(binding)
        }
    }
}
