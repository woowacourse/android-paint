package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColorsAdapter(
    private val colors: List<Int>,
    private val onColorClicked: (color: Int) -> Unit
) : RecyclerView.Adapter<ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder.of(
            parent = parent,
            onClicked = onColorClicked
        )
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        return holder.bind(colors[position])
    }
}
