package woowacourse.paint.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.HolderBrushTypeBinding
import woowacourse.paint.model.BrushType
import woowacourse.paint.ui.BrushTypeActionHandler

class BrushTypeAdapter(private val actionHandler: BrushTypeActionHandler) :
    ListAdapter<BrushType, BrushTypeAdapter.DrawColorViewHolder>(DrawingColorDiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DrawColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            HolderBrushTypeBinding.inflate(inflater, parent, false)
        binding.actionHandler = actionHandler
        return DrawColorViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DrawColorViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    class DrawColorViewHolder(private val binding: HolderBrushTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BrushType) {
            binding.brushType = item
        }
    }

    companion object DrawingColorDiffCallback : DiffUtil.ItemCallback<BrushType>() {
        override fun areItemsTheSame(
            oldItem: BrushType,
            newItem: BrushType,
        ): Boolean = oldItem.value == newItem.value

        override fun areContentsTheSame(
            oldItem: BrushType,
            newItem: BrushType,
        ): Boolean = oldItem == newItem
    }
}
