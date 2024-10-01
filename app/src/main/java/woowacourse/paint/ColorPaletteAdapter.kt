package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorPaletteBinding

class ColorPaletteAdapter(
    private val colorPaletteHandler: ColorPaletteHandler,
) : RecyclerView.Adapter<ColorPaletteViewHolder>() {
    private val colors =
        listOf(
            R.color.red,
            R.color.orange,
            R.color.yellow,
            R.color.green,
            R.color.blue,
        )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ColorPaletteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemColorPaletteBinding.inflate(inflater, parent, false)
        return ColorPaletteViewHolder(binding, colorPaletteHandler)
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    override fun onBindViewHolder(
        holder: ColorPaletteViewHolder,
        position: Int,
    ) {
        holder.bind(colors[position])
    }
}
