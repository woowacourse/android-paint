package woowacourse.paint.ui

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class ColorAdapter(
    private val onClickListener: (Int) -> Unit,
) : ListAdapter<Int, ColorViewHolder>(diffUtil) {

    init {
        submitList(defaultColors)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder.from(parent, onClickListener)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }
        }

        private val defaultColors = listOf(
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.YELLOW,
            Color.BLACK,
            Color.WHITE,
            Color.CYAN,
            Color.MAGENTA,
            Color.GRAY,
            Color.LTGRAY,
            Color.DKGRAY,
            Color.TRANSPARENT,
        )
    }
}
