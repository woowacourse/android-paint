package woowacourse.paint.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.main.viewholder.ColorViewHolder
import woowacourse.paint.model.PaintColor

class ColorAdapter(
    private val onColorClickListener: (PaintColor) -> Unit,
) : RecyclerView.Adapter<ColorViewHolder>() {
    private val colors = listOf(
        PaintColor(R.color.red, true),
        PaintColor(R.color.orange, false),
        PaintColor(R.color.yellow, false),
        PaintColor(R.color.green, false),
        PaintColor(R.color.blue, false),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(parent) {
            onColorClickListener(colors[it])
        }
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}
