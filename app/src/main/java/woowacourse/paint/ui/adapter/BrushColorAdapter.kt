package woowacourse.paint.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.HolderBrushColorBinding
import woowacourse.paint.model.BrushStyle
import woowacourse.paint.ui.BrushColorActionHandler

class BrushColorAdapter(private val actionHandler: BrushColorActionHandler) :
    ListAdapter<BrushStyle, BrushColorAdapter.DrawColorViewHolder>(DrawingColorDiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DrawColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            HolderBrushColorBinding.inflate(inflater, parent, false)
        binding.actionHandler = actionHandler
        return DrawColorViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DrawColorViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    class DrawColorViewHolder(private val binding: HolderBrushColorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BrushStyle) {
            binding.brushStyle = item
        }
    }

    companion object DrawingColorDiffCallback : DiffUtil.ItemCallback<BrushStyle>() {
        override fun areItemsTheSame(
            oldItem: BrushStyle,
            newItem: BrushStyle,
        ): Boolean = oldItem.color == newItem.color

        override fun areContentsTheSame(
            oldItem: BrushStyle,
            newItem: BrushStyle,
        ): Boolean = oldItem == newItem
    }
}
