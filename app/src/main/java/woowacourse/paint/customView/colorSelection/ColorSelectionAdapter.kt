package woowacourse.paint.customView.colorSelection

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.ColorPalette

class ColorSelectionAdapter(private val onClickListener: (ColorPalette) -> Unit) :
    RecyclerView.Adapter<ColorSelectionViewHolder>() {

    private val colors: List<ColorPalette> = ColorPalette.values().toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorSelectionViewHolder {
        return ColorSelectionViewHolder.from(parent, onClickListener)
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ColorSelectionViewHolder, position: Int) {
        holder.bind(colors[position])
    }
}
