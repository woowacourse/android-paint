package woowacourse.paint.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.main.viewholder.ColorViewHolder
import woowacourse.paint.model.PaintColor

class ColorAdapter(
    private val onColorClickListener: (PaintColor) -> Unit,
) : RecyclerView.Adapter<ColorViewHolder>() {
    private val colors = PaintColor.values()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(parent) {
            onColorClickListener(it)
        }
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}
