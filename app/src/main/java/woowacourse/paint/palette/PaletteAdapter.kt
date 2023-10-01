package woowacourse.paint.palette

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ViewPaletteBinding

class PaletteAdapter(private val data: List<Int>, private val onClickPalette: (Int) -> Unit) :
    RecyclerView.Adapter<PaletteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteViewHolder {
        return PaletteViewHolder(
            ViewPaletteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) {
            onClickPalette(data[it])
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PaletteViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
