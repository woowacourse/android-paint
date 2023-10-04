package woowacourse.paint.palette

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.databinding.ItemPaletteBinding
import woowacourse.paint.presentation.uimodel.BrushColorUiModel

class PaletteViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_palette, parent, false)) {

    private val binding = ItemPaletteBinding.bind(itemView)

    fun bind(brushColorUiModel: BrushColorUiModel, onPaletteClick: (BrushColorUiModel) -> Unit) {
        binding.palette.apply {
            setBackgroundColor(brushColorUiModel.color)
            setOnClickListener {
                onPaletteClick(brushColorUiModel)
            }
        }
    }
}
