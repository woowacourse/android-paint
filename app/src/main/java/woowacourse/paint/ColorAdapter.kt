package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(
    private val colors: List<Int>,
    private val onColorClickListener: OnColorClickListener,
) : RecyclerView.Adapter<ColorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder.from(parent, onColorClickListener)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        return holder.bind(colors[position])
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}
