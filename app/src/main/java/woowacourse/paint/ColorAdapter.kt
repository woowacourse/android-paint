package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.model.ColorBox

class ColorAdapter(
    private val onColorClickListener: OnColorClickListener,
) : ListAdapter<ColorBox, ColorViewHolder>(ColorDiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder.from(parent, onColorClickListener)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }

    fun setColors(colors: List<ColorBox>) {
        submitList(colors)
    }
}
