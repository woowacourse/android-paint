package woowacourse.paint.ui.colorpicker

import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorPickerBinding
import woowacourse.paint.model.PaintColor

class ColorPickerViewHolder(
    private val binding: ItemColorPickerBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(color: PaintColor) {
        binding.paintColor = color
    }
}
