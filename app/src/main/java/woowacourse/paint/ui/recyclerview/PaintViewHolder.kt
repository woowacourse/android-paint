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
        fun getView(parent: ViewGroup): ColorBinding {
            val layoutInflater = LayoutInflater.from(parent.context)
            return ColorBinding.inflate(layoutInflater, parent, false)
        }
    }
}
