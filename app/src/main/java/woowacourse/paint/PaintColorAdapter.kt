package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.databinding.ItemPaintColorBinding

class PaintColorAdapter(private val actionHandler: PaintActionHandler) :
    ListAdapter<PaintColor, PaintColorViewHolder>(diffUtil) {
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
            color = getItem(position).color,
            isChecked = getItem(position).isChecked,
            actionHandler = actionHandler
        )
    }

    companion object {
        val diffUtil =
            object : DiffUtil.ItemCallback<PaintColor>() {
                override fun areContentsTheSame(
                    oldItem: PaintColor,
                    newItem: PaintColor,
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areItemsTheSame(
                    oldItem: PaintColor,
                    newItem: PaintColor,
                ): Boolean {
                    return oldItem === newItem
                }
            }
    }
}
