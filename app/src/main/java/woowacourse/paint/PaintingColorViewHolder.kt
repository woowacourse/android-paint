package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorBinding
import woowacourse.paint.model.PaintingColor

class PaintingColorViewHolder(
    private val binding: ItemColorBinding,
    private val setPaintingColor: (paintingColor: PaintingColor) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    private var paintingColor: PaintingColor? = null

    init {
        itemView.setOnClickListener {
            setPaintingColor(paintingColor ?: return@setOnClickListener)
        }
    }

    fun bind(paintingColor: PaintingColor) {
        this.paintingColor = paintingColor
        binding.tvColor.setBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                paintingColor.colorRes,
            ),
        )
    }

    companion object {
        fun from(
            parent: ViewGroup,
            setPaintingColor: (paintingColor: PaintingColor) -> Unit,
        ): PaintingColorViewHolder {
            val binding =
                ItemColorBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
            return PaintingColorViewHolder(binding, setPaintingColor)
        }
    }
}
