package woowacourse.paint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.action.PaintActionHandler
import woowacourse.paint.databinding.ItemPaintColorBinding
import woowacourse.paint.uimodel.PaintColorUiModel

class PaintColorAdapter(private val actionHandler: PaintActionHandler) :
    ListAdapter<PaintColorUiModel, PaintColorViewHolder>(diffUtil) {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PaintColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPaintColorBinding.inflate(inflater, parent, false)
        return PaintColorViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PaintColorViewHolder,
        position: Int,
    ) {
        holder.bind(
            paintColorUiModel = getItem(position),
            actionHandler = actionHandler,
        )
    }

    companion object {
        val diffUtil =
            object : DiffUtil.ItemCallback<PaintColorUiModel>() {
                override fun areContentsTheSame(
                    oldItem: PaintColorUiModel,
                    newItem: PaintColorUiModel,
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areItemsTheSame(
                    oldItem: PaintColorUiModel,
                    newItem: PaintColorUiModel,
                ): Boolean {
                    return oldItem === newItem
                }
            }
    }
}
