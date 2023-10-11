package woowacourse.paint.ui.glocanvas

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.ui.model.DrawingToolModel
import woowacourse.paint.ui.model.SelectableDrawingToolModel

class DrawingToolSettingsAdapter(
    private val onItemClick: (DrawingToolModel) -> Unit,
) : ListAdapter<SelectableDrawingToolModel, DrawingToolSettingsViewHolder>(BrushDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawingToolSettingsViewHolder {
        return DrawingToolSettingsViewHolder.create(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: DrawingToolSettingsViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun updateDrawingTools(brushes: List<SelectableDrawingToolModel>) {
        submitList(brushes)
    }

    companion object {
        private val BrushDiffUtil = object : DiffUtil.ItemCallback<SelectableDrawingToolModel>() {
            override fun areItemsTheSame(
                oldItem: SelectableDrawingToolModel,
                newItem: SelectableDrawingToolModel,
            ): Boolean {
                return oldItem.drawingTool == newItem.drawingTool
            }

            override fun areContentsTheSame(
                oldItem: SelectableDrawingToolModel,
                newItem: SelectableDrawingToolModel,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
