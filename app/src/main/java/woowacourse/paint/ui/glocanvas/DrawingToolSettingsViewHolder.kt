package woowacourse.paint.ui.glocanvas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemDrawingToolBinding
import woowacourse.paint.ui.model.DrawingToolModel
import woowacourse.paint.ui.model.SelectableDrawingToolModel

class DrawingToolSettingsViewHolder private constructor(
    private val binding: ItemDrawingToolBinding,
    onDrawingToolChanged: (DrawingToolModel) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.ivDrawingTool.setOnClickListener {
            binding.drawingTool?.let {
                onDrawingToolChanged(it)
            }
        }
    }

    fun bind(drawingToolModel: SelectableDrawingToolModel) {
        binding.drawingTool = drawingToolModel.drawingTool
        binding.ivDrawingTool.isSelected = drawingToolModel.isSelected
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onDrawingToolChanged: (DrawingToolModel) -> Unit,
        ): DrawingToolSettingsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemDrawingToolBinding.inflate(layoutInflater, parent, false)
            return DrawingToolSettingsViewHolder(binding, onDrawingToolChanged)
        }
    }
}
