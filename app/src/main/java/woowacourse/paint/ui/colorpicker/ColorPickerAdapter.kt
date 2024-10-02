package woowacourse.paint.ui.colorpicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.databinding.ItemColorPickerBinding
import woowacourse.paint.model.PaintColor
import woowacourse.paint.ui.ColorPickerHandler

class ColorPickerAdapter(
    private val handler: ColorPickerHandler,
) : ListAdapter<PaintColor, ColorPickerViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ColorPickerViewHolder {
        val binding =
            ItemColorPickerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.clickHandler = handler
        return ColorPickerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ColorPickerViewHolder,
        position: Int,
    ) {
        holder.bind(currentList[position])
    }

    class DiffCallback : DiffUtil.ItemCallback<PaintColor>() {
        override fun areItemsTheSame(
            oldItem: PaintColor,
            newItem: PaintColor,
        ): Boolean = oldItem.color == newItem.color

        override fun areContentsTheSame(
            oldItem: PaintColor,
            newItem: PaintColor,
        ): Boolean = (oldItem == newItem)
    }
}
