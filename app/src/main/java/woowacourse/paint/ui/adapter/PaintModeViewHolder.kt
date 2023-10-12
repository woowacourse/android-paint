package woowacourse.paint.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.PaintModeItemBinding
import woowacourse.paint.ui.PaintMode

class PaintModeViewHolder private constructor(
    private val binding: PaintModeItemBinding,
    private val context: Context,
    onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.paletteItemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    fun bind(paintMode: PaintMode) {
        binding.icon = AppCompatResources.getDrawable(context, paintMode.drawable)
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: (Int) -> Unit
        ): PaintModeViewHolder {
            val binding =
                PaintModeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PaintModeViewHolder(binding, binding.root.context, onItemClick)
        }
    }
}
