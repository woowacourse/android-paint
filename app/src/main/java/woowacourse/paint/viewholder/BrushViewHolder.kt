package woowacourse.paint.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemBrushBinding
import woowacourse.paint.listener.OnBrushClickListener
import woowacourse.paint.model.BrushBox

class BrushViewHolder private constructor(
    private val binding: ItemBrushBinding,
    onBrushClickListener: OnBrushClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.listener = onBrushClickListener
    }

    fun bind(brush: BrushBox) {
        binding.btnBrush.setImageResource(brush.brushTool.image)
        binding.btnBrush.isSelected = brush.isSelected
        binding.paintBrush = brush
    }

    companion object {
        fun from(
            parent: ViewGroup,
            onBrushClickListener: OnBrushClickListener,
        ): BrushViewHolder {
            val binding = ItemBrushBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return BrushViewHolder(binding, onBrushClickListener)
        }
    }
}
