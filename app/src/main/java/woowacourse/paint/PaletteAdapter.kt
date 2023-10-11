package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.model.palettecolor.PaletteColor

class PaletteAdapter(
    private val backgroundColors: List<PaletteColor>,
    private val onClick: (hexCode: PaletteColor) -> Unit,
) :
    RecyclerView.Adapter<PaletteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteViewHolder {
        return PaletteViewHolder(parent, onClick)
    }

    override fun getItemCount(): Int = backgroundColors.size

    override fun onBindViewHolder(holder: PaletteViewHolder, position: Int) {
        holder.bind(backgroundColors[position])
    }
}
