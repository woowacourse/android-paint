package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemBrushBinding

class BrushSettingToolViewHolder private constructor(
    private val binding: ItemBrushBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(brushModel: BrushModel) {
        binding.brush = brushModel.brush
        binding.ivBrush.isSelected = brushModel.isSelected
    }

    companion object {
        fun create(parent: ViewGroup): BrushSettingToolViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemBrushBinding.inflate(layoutInflater, parent, false)
            return BrushSettingToolViewHolder(binding)
        }
    }
}
