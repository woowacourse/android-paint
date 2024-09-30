package woowacourse.paint.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.paint.databinding.ItemColorPaletteBinding

class ColorPaletteAdapter(
    private val colorResIds: List<Int>,
    private val colorPaletteListener: ColorPaletteListener,
) : RecyclerView.Adapter<ColorPaletteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPaletteViewHolder {
        val binding =
            ItemColorPaletteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorPaletteViewHolder(binding)
    }

    override fun getItemCount(): Int = colorResIds.size

    override fun onBindViewHolder(holder: ColorPaletteViewHolder, position: Int) {
        holder.bind(colorResIds[position], colorPaletteListener)
    }
}
