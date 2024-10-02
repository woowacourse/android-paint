package woowacourse.paint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.Color
import woowacourse.paint.ColorPaletteHandler
import woowacourse.paint.databinding.ItemColorPaletteBinding

class ColorPaletteAdapter(
    private val colorPaletteHandler: ColorPaletteHandler,
) : RecyclerView.Adapter<ColorPaletteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ColorPaletteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemColorPaletteBinding.inflate(inflater, parent, false)
        return ColorPaletteViewHolder(binding, colorPaletteHandler)
    }

    override fun getItemCount(): Int {
        return Color.entries.size
    }

    override fun onBindViewHolder(
        holder: ColorPaletteViewHolder,
        position: Int,
    ) {
        holder.bind(Color.entries[position].colorId)
    }
}
