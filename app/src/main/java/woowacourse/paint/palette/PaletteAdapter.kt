package woowacourse.paint.palette

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaletteBinding
import woowacourse.paint.presentation.uimodel.BrushColorUiModel

class PaletteAdapter(
    private val colors: List<BrushColorUiModel>,
    private val onPaletteClick: (Int) -> Unit,
) :
    RecyclerView.Adapter<PaletteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteViewHolder {
        return PaletteViewHolder(
            ItemPaletteBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: PaletteViewHolder, position: Int) {
        holder.bind(position, colors[position].color) { onPaletteClick(position) }
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}
