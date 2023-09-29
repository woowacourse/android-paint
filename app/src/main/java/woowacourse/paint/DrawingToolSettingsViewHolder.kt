package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemDrawingToolBinding

class DrawingToolSettingsViewHolder private constructor(
    private val binding: ItemDrawingToolBinding,
    onDrawingToolChanged: (DrawingTool) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.ivDrawingTool.setOnClickListener {
            binding.drawingTool?.let {
                onDrawingToolChanged(it)
            }
        }
    }

    fun bind(drawingToolModel: DrawingToolModel) {
        binding.drawingTool = drawingToolModel.drawingTool
        binding.ivDrawingTool.isSelected = drawingToolModel.isSelected
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onDrawingToolChanged: (DrawingTool) -> Unit,
        ): DrawingToolSettingsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemDrawingToolBinding.inflate(layoutInflater, parent, false)
            return DrawingToolSettingsViewHolder(binding, onDrawingToolChanged)
        }
    }
}
