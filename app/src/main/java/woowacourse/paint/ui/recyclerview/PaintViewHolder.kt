package woowacourse.paint.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ColorBinding

class PaintViewHolder(
    private val binding: ColorBinding,
    private val onColorClick: (color: Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.view.setOnClickListener {
            val color = binding.color ?: return@setOnClickListener
            onColorClick(color)
        }
    }

    fun bind(color: Int) {
        binding.color = color
    }

    companion object {
        fun from(
            parent: ViewGroup,
            onColorClick: (color: Int) -> Unit,
        ): PaintViewHolder {
            val binding = ColorBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return PaintViewHolder(binding, onColorClick)
        }
    }
}
