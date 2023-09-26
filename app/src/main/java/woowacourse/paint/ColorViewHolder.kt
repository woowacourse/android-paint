package woowacourse.paint

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorBinding

class ColorViewHolder(
    private val binding: ItemColorBinding,
    private val onClick: (color: Int) -> Unit,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener { onClick(Color.valueOf(adapterPosition)) }
    }

    fun bind(colors: Color) {
        val color = ContextCompat.getColor(context, colors.colorRes)

        binding.itemColorPaint.setBackgroundColor(color)
    }

    companion object {

        fun from(
            parent: ViewGroup,
            onClick: (color: Int) -> Unit,
            context: Context
        ): ColorViewHolder = ColorViewHolder(
            ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick,
            context
        )
    }
}