package woowacourse.paint.presentation.ui.main.colors

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColorsAdapter(
    private val itemColors: List<ItemColor>,
) : RecyclerView.Adapter<ColorItemViewHolder>() {

    override fun getItemCount(): Int = itemColors.size

    override fun onBindViewHolder(holder: ColorItemViewHolder, position: Int) {
        holder.bind(itemColors[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorItemViewHolder {
        return ColorItemViewHolder.of(parent)
    }
}
