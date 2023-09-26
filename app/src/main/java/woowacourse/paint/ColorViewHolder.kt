package woowacourse.paint

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.Color.BLACK
import woowacourse.paint.Color.BLUE
import woowacourse.paint.Color.GREEN
import woowacourse.paint.Color.ORANGE
import woowacourse.paint.Color.RED
import woowacourse.paint.Color.YELLOW
import woowacourse.paint.databinding.ItemColorBinding

class ColorViewHolder(
    private val binding: ItemColorBinding,
    private val onClick: (color: Color) -> Unit,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener { onClick(Color.valueOf(adapterPosition)) }
    }

    fun bind(colors: Color) {
        val color = ContextCompat.getColor(
            context, when (colors) {
                RED -> R.color.red
                ORANGE -> R.color.orange
                YELLOW -> R.color.yellow
                GREEN -> R.color.green
                BLUE -> R.color.blue
                BLACK -> R.color.black
            }
        )

        binding.itemColorPaint.setBackgroundColor(color)
    }

    companion object {

        fun from(
            parent: ViewGroup,
            onClick: (color: Color) -> Unit,
            context: Context
        ): ColorViewHolder = ColorViewHolder(
            ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick,
            context
        )
    }
}