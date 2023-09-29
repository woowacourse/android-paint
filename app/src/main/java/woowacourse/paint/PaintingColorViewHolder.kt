package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaintingColorBinding

class PaintingColorViewHolder(
    private val binding: ItemPaintingColorBinding,
    private val setPaintingColor: (paintingColor: Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var paintingColor: Int? = null

    init {
        itemView.setOnClickListener {
            setPaintingColor(paintingColor ?: return@setOnClickListener)
        }
    }

    fun bind(paintingColor: Int) {
        this.paintingColor = paintingColor
        binding.ivColor.setBackgroundColor(paintingColor)
    }

    companion object {
        fun from(
            parent: ViewGroup,
            setPaintingColor: (paintingColor: Int) -> Unit,
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
