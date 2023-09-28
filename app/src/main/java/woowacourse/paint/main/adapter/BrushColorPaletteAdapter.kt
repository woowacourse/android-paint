package woowacourse.paint.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.main.ColorClickListener
import woowacourse.paint.main.model.BrushColorBox

class BrushColorPaletteAdapter(
    private val colorClickListener: ColorClickListener,
) : ListAdapter<BrushColorBox, BrushColorPaletteViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushColorPaletteViewHolder {
        return BrushColorPaletteViewHolder.from(parent, colorClickListener)
    }

    override fun onBindViewHolder(holder: BrushColorPaletteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<BrushColorBox>() {
            override fun areItemsTheSame(oldItem: BrushColorBox, newItem: BrushColorBox): Boolean =
                oldItem.brushColor.ordinal == newItem.brushColor.ordinal

            override fun areContentsTheSame(
                oldItem: BrushColorBox,
                newItem: BrushColorBox,
            ): Boolean = oldItem == newItem
        }
    }
}
