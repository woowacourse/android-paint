package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class DrawingToolSettingsAdapter(
    private val onBrushChanged: (DrawingTool) -> Unit,
) : ListAdapter<DrawingToolModel, DrawingToolSettingsViewHolder>(BrushDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawingToolSettingsViewHolder {
        return DrawingToolSettingsViewHolder.create(parent, onBrushChanged)
    }

    override fun onBindViewHolder(holder: DrawingToolSettingsViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun updateDrawingTools(brushes: List<DrawingToolModel>) {
        submitList(brushes)
    }

    companion object {
        private val BrushDiffUtil = object : DiffUtil.ItemCallback<DrawingToolModel>() {
            override fun areItemsTheSame(
                oldItem: DrawingToolModel,
                newItem: DrawingToolModel,
            ): Boolean {
                return oldItem.drawingTool == newItem.drawingTool
            }

            override fun areContentsTheSame(
                oldItem: DrawingToolModel,
                newItem: DrawingToolModel,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
