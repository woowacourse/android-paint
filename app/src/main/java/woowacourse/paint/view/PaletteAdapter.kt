package woowacourse.paint.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaintBinding
import woowacourse.paint.utils.ItemDiffCallback

class PaletteAdapter(
    private val onClickPaint: (Paint) -> Unit,
) : ListAdapter<Paint, PaletteAdapter.PaintViewHolder>(paintComparator) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PaintViewHolder {
        val binding =
            ItemPaintBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return PaintViewHolder(binding, onClickPaint)
    }

    override fun onBindViewHolder(
        holder: PaintViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    class PaintViewHolder(
        private val binding: ItemPaintBinding,
        private val onClickPaint: (Paint) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClickPaint(binding.paintItem ?: return@setOnClickListener)
            }
        }

        fun bind(paint: Paint) {
            binding.paintItem = paint
            binding.executePendingBindings()
        }
    }

    private companion object {
        val paintComparator =
            ItemDiffCallback<Paint>(
                onItemsTheSame = { oldItem, newItem -> oldItem.color == newItem.color },
                onContentsTheSame = { oldItem, newItem -> oldItem == newItem },
            )
    }
}
