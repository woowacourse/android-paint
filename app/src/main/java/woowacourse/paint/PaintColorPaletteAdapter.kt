package woowacourse.paint

import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView

class PaintColorPaletteAdapter(@ColorRes val colors: List<Int>) :
    RecyclerView.Adapter<PaintColorPaletteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaintColorPaletteViewHolder {
        return PaintColorPaletteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PaintColorPaletteViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int = colors.size
}
