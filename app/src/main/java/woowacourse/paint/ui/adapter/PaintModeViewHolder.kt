package woowacourse.paint.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.PaintModeItemBinding
import woowacourse.paint.ui.model.PaintModeModel

class PaintModeViewHolder private constructor(
    private val binding: PaintModeItemBinding,
    onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.paletteItemView.setOnClickListener { onItemClick(adapterPosition) }
    }

    fun bind(paintModeModel: PaintModeModel) {
        binding.icon = paintModeModel.drawable
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: (Int) -> Unit
        ): PaintModeViewHolder {
            val binding =
                PaintModeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PaintModeViewHolder(binding, onItemClick)
        }
    }
}
