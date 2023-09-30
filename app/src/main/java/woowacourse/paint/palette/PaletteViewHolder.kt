package woowacourse.paint.palette

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaletteBinding
import woowacourse.paint.presentation.uimodel.BrushColorUiModel

class PaletteViewHolder(private val binding: ItemPaletteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(brushColorUiModel: BrushColorUiModel, onPaletteClick: (BrushColorUiModel) -> Unit) {
        binding.palette.apply {
            setBackgroundColor(brushColorUiModel.color)
            setOnClickListener {
                onPaletteClick(brushColorUiModel)
            }
        }
    }
}
