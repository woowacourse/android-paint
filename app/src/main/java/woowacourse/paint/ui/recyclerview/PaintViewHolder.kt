package woowacourse.paint.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ColorBinding

class PaintViewHolder(
    private val binding: ColorBinding,
    private val onColorClick: (color: Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(color: Int) {
        binding.color = color
        setOnClickListener(color)
    }

    private fun setOnClickListener(color: Int) {
        binding.view.setOnClickListener {
            onColorClick(color)
        }
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
