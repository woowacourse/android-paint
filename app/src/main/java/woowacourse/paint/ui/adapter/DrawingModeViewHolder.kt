package woowacourse.paint.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.PaintModeItemBinding
import woowacourse.paint.model.BrushTools

class DrawingModeViewHolder private constructor(
    private val binding: PaintModeItemBinding,
    private val context: Context,
    onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.paletteItemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    fun bind(brushTools: BrushTools) {
        binding.icon = AppCompatResources.getDrawable(context, brushTools.drawable)
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: (Int) -> Unit
        ): DrawingModeViewHolder {
            val binding =
                PaintModeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DrawingModeViewHolder(binding, binding.root.context, onItemClick)
        }
    }
}
