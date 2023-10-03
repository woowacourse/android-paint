package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaintingColorBinding

class PaintingColorViewHolder(
    private val binding: ItemPaintingColorBinding,
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
        binding.ivColor.setBackgroundColor(
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
            val binding = ItemPaintingColorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
            return PaintingColorViewHolder(binding, setPaintingColor)
        }
    }
}
