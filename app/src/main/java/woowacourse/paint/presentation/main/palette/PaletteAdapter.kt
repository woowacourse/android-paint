package woowacourse.paint.presentation.main.palette

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.presentation.uimodel.BrushColorUiModel

class PaletteAdapter(
    private val colors: List<BrushColorUiModel>,
    private val onPaletteClick: (BrushColorUiModel) -> Unit,
) :
    RecyclerView.Adapter<PaletteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteViewHolder {
        return PaletteViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PaletteViewHolder, position: Int) {
        holder.bind(colors[position]) { onPaletteClick(colors[position]) }
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}
