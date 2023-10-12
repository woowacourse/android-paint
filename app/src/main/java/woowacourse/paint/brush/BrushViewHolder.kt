package woowacourse.paint.brush

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.databinding.ItemBrushBinding
import woowacourse.paint.model.Brush

class BrushViewHolder(
    parent: ViewGroup,
    onBrushClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_brush, parent, false),
) {
    private val binding: ItemBrushBinding = ItemBrushBinding.bind(itemView)

    init {
        itemView.setOnClickListener {
            onBrushClick(adapterPosition)
        }
    }

    fun bind(brush: Brush) {
        binding.brush = brush

        with(itemView.context) {
            binding.btnBrush.text = when (brush) {
                Brush.PEN -> getString(R.string.main_btn_change_brush_pen)
                Brush.CIRCLE -> getString(R.string.main_btn_change_brush_circle)
                Brush.RECT -> getString(R.string.main_btn_change_brush_rectangle)
                Brush.ERASER -> getString(R.string.main_btn_change_brush_eraser)
            }
        }
    }
}
