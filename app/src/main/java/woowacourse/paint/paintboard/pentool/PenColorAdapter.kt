package woowacourse.paint.paintboard.pentool

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.RvItemColorBinding

class PenColorAdapter(private val selectColor: (position: Int) -> Unit) :
    ListAdapter<PenColor, PenColorAdapter.PenColorViewHolder>(PenColorComparator) {
    class PenColorViewHolder(
        private val binding: RvItemColorBinding,
        selectColor: (position: Int) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                selectColor(adapterPosition)
            }
        }

        fun bind(data: PenColor) {
            binding.viewRvItemColorColor.backgroundTintList =
                ContextCompat.getColorStateList(binding.root.context, data.color)
            if (data.isSelected) {
                binding.ivRvItemColorCheck.visibility = View.VISIBLE
            } else {
                binding.ivRvItemColorCheck.visibility = View.GONE
            }
        }

        companion object {
            fun getInstance(
                parent: ViewGroup,
                selectColor: (position: Int) -> Unit,
            ): PenColorViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RvItemColorBinding.inflate(layoutInflater)
                return PenColorViewHolder(binding, selectColor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenColorViewHolder {
        return PenColorViewHolder.getInstance(parent, selectColor)
    }

    override fun onBindViewHolder(holder: PenColorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object PenColorComparator : DiffUtil.ItemCallback<PenColor>() {
        override fun areItemsTheSame(oldItem: PenColor, newItem: PenColor): Boolean {
            return oldItem.color == newItem.color
        }

        override fun areContentsTheSame(oldItem: PenColor, newItem: PenColor): Boolean {
            return oldItem == newItem
        }
    }
}
