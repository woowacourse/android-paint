package woowacourse.paint.presentation.main.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.databinding.ItemToolBinding
import woowacourse.paint.presentation.uimodel.BrushColorUiModel
import woowacourse.paint.presentation.uimodel.BrushTypeUiModel

class BrushToolViewHolder(
    parent: ViewGroup,
    private val onClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_tool, parent, false),
) {
    private var binding = ItemToolBinding.bind(itemView)

    init {
        binding.btnItemTool.setOnClickListener { onClick(adapterPosition) }
    }

    fun bind(brushTypeUiModel: BrushTypeUiModel) {
        binding.btnItemTool.text = brushTypeUiModel.description
    }

    fun bind(brushColorUiModel: BrushColorUiModel) {
        binding.btnItemTool.setBackgroundColor(brushColorUiModel.color)
    }
}
