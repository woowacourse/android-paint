package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemToolBinding
import woowacourse.paint.painting.PaintingType
import woowacourse.paint.painting.PaintingType.CIRCLE
import woowacourse.paint.painting.PaintingType.ERASER
import woowacourse.paint.painting.PaintingType.LINE
import woowacourse.paint.painting.PaintingType.RECTANGLE

class PaintingTypeViewHolder(
    private val binding: ItemToolBinding,
    private val setPaintingType: (paintingType: PaintingType) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var paintingType: PaintingType? = null

    init {
        itemView.setOnClickListener {
            setPaintingType(paintingType ?: return@setOnClickListener)
        }
    }

    fun bind(paintingType: PaintingType) {
        this.paintingType = paintingType
        binding.buttonTool.text = getPaintingTypeText(paintingType)
    }

    private fun getPaintingTypeText(paintingType: PaintingType): String {
        return when (paintingType) {
            ERASER -> binding.root.context.getString(R.string.eraser)
            LINE -> binding.root.context.getString(R.string.pen)
            RECTANGLE -> binding.root.context.getString(R.string.rectangle)
            CIRCLE -> binding.root.context.getString(R.string.circle)
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            setPaintingType: (paintingType: PaintingType) -> Unit,
        ): PaintingTypeViewHolder {
            val binding = ItemToolBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
            return PaintingTypeViewHolder(binding, setPaintingType)
        }
    }
}
