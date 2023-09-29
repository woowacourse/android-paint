package woowacourse.paint.palette

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaletteBinding

class PaletteAdapter(
    private val colors: List<Int>,
    private val onPaletteClick: (Int) -> Unit,
) :
    RecyclerView.Adapter<PaletteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteViewHolder {
        return PaletteViewHolder(
            ItemPaletteBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: PaletteViewHolder, position: Int) {
        holder.bind(position) { onPaletteClick(position) }
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}
