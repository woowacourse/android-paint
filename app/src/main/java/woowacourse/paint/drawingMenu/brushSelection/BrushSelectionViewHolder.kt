package woowacourse.paint.drawingMenu.brushSelection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemOptionSelectionBinding
import woowacourse.paint.model.BrushType

class BrushSelectionViewHolder(
    private val binding: ItemOptionSelectionBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        brushType: BrushType,
        onClickBrushListener: (BrushType) -> Unit,
    ) {
        binding.optionItem.background =
            ContextCompat.getDrawable(binding.root.context, brushType.icon)
        binding.optionItem.setOnClickListener { onClickBrushListener(brushType) }
    }

    companion object {

        fun from(
            parent: ViewGroup,
        ): BrushSelectionViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemOptionSelectionBinding.inflate(layoutInflater, parent, false)
            return BrushSelectionViewHolder(binding)
        }
    }
}
