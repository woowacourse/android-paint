package woowacourse.paint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.action.PaintActionHandler
import woowacourse.paint.databinding.ItemPaintDrawingModeBinding
import woowacourse.paint.uimodel.DrawingModeUiModel

class DrawingModeAdapter(private val actionHandler: PaintActionHandler) :
    ListAdapter<DrawingModeUiModel, DrawingModeViewHolder>(diffUtil) {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DrawingModeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPaintDrawingModeBinding.inflate(inflater, parent, false)
        return DrawingModeViewHolder(binding, actionHandler)
    }

    override fun onBindViewHolder(
        holder: DrawingModeViewHolder,
        position: Int,
    ) {
        holder.bind(drawingModeUiModel = getItem(position))
    }

    companion object {
        val diffUtil =
            object : DiffUtil.ItemCallback<DrawingModeUiModel>() {
                override fun areContentsTheSame(
                    oldItem: DrawingModeUiModel,
                    newItem: DrawingModeUiModel,
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areItemsTheSame(
                    oldItem: DrawingModeUiModel,
                    newItem: DrawingModeUiModel,
                ): Boolean {
                    return oldItem === newItem
                }
            }
    }
}
