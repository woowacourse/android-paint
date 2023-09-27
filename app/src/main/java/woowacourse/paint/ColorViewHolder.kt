package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorBinding

class ColorViewHolder private constructor(
    private val binding: ItemColorBinding,
    onColorClickListener: OnColorClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.listener = onColorClickListener
    }

    fun bind(color: Int) {
        binding.tvColor.setBackgroundResource(color)
        binding.color = color
    }

    companion object {
        fun from(
            parent: ViewGroup,
            onColorClickListener: OnColorClickListener,
        ): ColorViewHolder {
            val binding = ItemColorBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return ColorViewHolder(binding, onColorClickListener)
        }
    }
}
