package woowacourse.paint.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.HolderDrawingColorBinding
import woowacourse.paint.model.DrawingStyle
import woowacourse.paint.ui.ActionHandler

class DrawingColorAdapter(private val actionHandler: ActionHandler) :
    ListAdapter<DrawingStyle, DrawingColorAdapter.DrawColorViewHolder>(DrawingColorDiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DrawColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            HolderDrawingColorBinding.inflate(inflater, parent, false)
        binding.actionHandler = actionHandler
        return DrawColorViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DrawColorViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    class DrawColorViewHolder(private val binding: HolderDrawingColorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DrawingStyle) {
            binding.drawingColor = item
        }
    }

    companion object DrawingColorDiffCallback : DiffUtil.ItemCallback<DrawingStyle>() {
        override fun areItemsTheSame(
            oldItem: DrawingStyle,
            newItem: DrawingStyle,
        ): Boolean = oldItem.color == newItem.color

        override fun areContentsTheSame(
            oldItem: DrawingStyle,
            newItem: DrawingStyle,
        ): Boolean = oldItem == newItem
    }
}
