package woowacourse.paint.ui.drawingmode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.databinding.ItemDrawingModeBinding
import woowacourse.paint.model.DrawingMode
import woowacourse.paint.ui.DrawingModeHandler

class DrawingModeAdapter(
    private val handler: DrawingModeHandler,
) : ListAdapter<DrawingMode, DrawingModeViewHolder>(DiffCallback()) {
    init {
        submitList(DrawingMode.getList())
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DrawingModeViewHolder {
        val binding =
            ItemDrawingModeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.clickHandler = handler
        return DrawingModeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DrawingModeViewHolder,
        position: Int,
    ) {
        holder.bind(currentList[position])
    }

    class DiffCallback : DiffUtil.ItemCallback<DrawingMode>() {
        override fun areItemsTheSame(
            oldItem: DrawingMode,
            newItem: DrawingMode,
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: DrawingMode,
            newItem: DrawingMode,
        ): Boolean = (oldItem == newItem)
    }
}
