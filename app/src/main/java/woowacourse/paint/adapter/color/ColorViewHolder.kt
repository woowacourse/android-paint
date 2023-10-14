package woowacourse.paint.adapter.color

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorBinding
import woowacourse.paint.model.Color

class ColorViewHolder(
    private val binding: ItemColorBinding,
    private val onColorClick: (color: Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener { onColorClick(Color.valueOf(adapterPosition)) }
    }

    fun bind(color: Color) {
        val colorRes = ContextCompat.getColor(binding.root.context, color.colorRes)

        binding.itemColorPaint.setBackgroundColor(colorRes)
    }

    companion object {

        fun from(
            parent: ViewGroup,
            onClick: (color: Int) -> Unit,
        ): ColorViewHolder = ColorViewHolder(
            ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick,
        )
    }
}