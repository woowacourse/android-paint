package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.brush.ColorPalette
import woowacourse.paint.databinding.ItemColorPaletteBinding

class ColorPaletteAdapter(
    private val colorPalettes: List<ColorPalette>,
    private val onClickColorPalette: (ColorPalette) -> Unit,
) : RecyclerView.Adapter<ColorPaletteAdapter.ColorPaletteViewHolder>() {

    class ColorPaletteViewHolder(
        private val binding: ItemColorPaletteBinding,
        private val onClickColorPalette: (ColorPalette) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(colorPalette: ColorPalette) {
            binding.paletteButton.apply {
                setBackgroundColor(colorPalette.color)
                setOnClickListener {
                    onClickColorPalette(colorPalette)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPaletteViewHolder {
        val binding =
            ItemColorPaletteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorPaletteViewHolder(binding,onClickColorPalette)
    }

    override fun getItemCount(): Int = colorPalettes.size

    override fun onBindViewHolder(holder: ColorPaletteViewHolder, position: Int) {
        holder.bind(colorPalettes[position])
    }
}
