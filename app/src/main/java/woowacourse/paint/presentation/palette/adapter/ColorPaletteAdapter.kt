package woowacourse.paint.presentation.palette.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorPaletteBinding
import woowacourse.paint.presentation.palette.ColorPaletteListener
import woowacourse.paint.presentation.palette.ColorUiModel

class ColorPaletteAdapter(
    private val colorUiModels: List<ColorUiModel>,
    private val colorPaletteListener: ColorPaletteListener,
) : RecyclerView.Adapter<ColorPaletteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ColorPaletteViewHolder {
        val binding =
            ItemColorPaletteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorPaletteViewHolder(binding)
    }

    override fun getItemCount(): Int = colorUiModels.size

    override fun onBindViewHolder(
        holder: ColorPaletteViewHolder,
        position: Int,
    ) {
        holder.bind(colorUiModels[position], colorPaletteListener)
    }
}
