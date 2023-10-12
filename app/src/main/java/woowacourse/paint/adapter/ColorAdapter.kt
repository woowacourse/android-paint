package woowacourse.paint.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.listener.OnColorClickListener
import woowacourse.paint.model.ColorBox
import woowacourse.paint.viewholder.ColorViewHolder

class ColorAdapter(
    private val onColorClickListener: OnColorClickListener,
) : ListAdapter<ColorBox, ColorViewHolder>(ColorDiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder.from(parent, onColorClickListener)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }
}
