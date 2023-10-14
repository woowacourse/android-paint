package woowacourse.paint.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PaletteAdapter(
    private val colors: List<Int>,
    private val onItemClick: (Int) -> Unit,
) : RecyclerView.Adapter<PaletteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteViewHolder {
        return PaletteViewHolder.create(parent) { position -> onItemClick(colors[position]) }
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: PaletteViewHolder, position: Int) {
        holder.bind(colors[position])
    }
}
