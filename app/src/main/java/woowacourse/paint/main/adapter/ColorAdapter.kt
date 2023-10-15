package woowacourse.paint.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.main.viewholder.ColorViewHolder
import woowacourse.paint.model.PaintColor

class ColorAdapter(
    private val onColorClickListener: (Int) -> Unit,
) : ListAdapter<PaintColor, ColorViewHolder>(ColorDiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(parent, onColorClickListener)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        object ColorDiffUtilCallback : DiffUtil.ItemCallback<PaintColor>() {
            override fun areItemsTheSame(oldItem: PaintColor, newItem: PaintColor): Boolean {
                return oldItem.colorRes == newItem.colorRes
            }

            override fun areContentsTheSame(oldItem: PaintColor, newItem: PaintColor): Boolean {
                return oldItem == newItem
            }
        }
    }
}
