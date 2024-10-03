package woowacourse.paint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.action.PaintActionHandler
import woowacourse.paint.databinding.ItemPaintDrawingModeBinding
import woowacourse.paint.uimodel.DrawingModeUiModel

class PaintDrawingModeAdapter(private val actionHandler: PaintActionHandler) :
    ListAdapter<DrawingModeUiModel, PaintDrawingModelHolder>(diffUtil) {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PaintDrawingModelHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPaintDrawingModeBinding.inflate(inflater, parent, false)
        return PaintDrawingModelHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PaintDrawingModelHolder,
        position: Int,
    ) {
        holder.bind(
            drawingModeUiModel = getItem(position),
            actionHandler = actionHandler,
        )
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
