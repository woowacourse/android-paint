package woowacourse.paint.palette

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColorSelectorAdapter(private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ColorSelectorViewHolder>() {

    private val colors = Color.values()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorSelectorViewHolder =
        ColorSelectorViewHolder(parent, onItemClick)

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ColorSelectorViewHolder, position: Int) {
        holder.bind(colors[position])
    }
}
