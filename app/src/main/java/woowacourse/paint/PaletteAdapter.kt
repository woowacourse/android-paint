package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PaletteAdapter(
    private val backgroundColors: List<Int>,
    private val onClick: (Int) -> Unit,
) :
    RecyclerView.Adapter<PaletteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteViewHolder {
        return PaletteViewHolder(parent) { onClick(backgroundColors[it]) }
    }

    override fun getItemCount(): Int = backgroundColors.size

    override fun onBindViewHolder(holder: PaletteViewHolder, position: Int) {
        holder.bind(backgroundColors[position])
    }
}
