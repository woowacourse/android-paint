package woowacourse.paint.paintboard.pentool

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class PenColorAdapter(private val selectColor: (position: Int) -> Unit) :
    ListAdapter<PenColor, PenColorViewHolder>(PenColorComparator) {

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
