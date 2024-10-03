package woowacourse.paint.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemPaletteColorBinding

class PaletteAdapter(
    private val colorRes: List<Int>,
    private val paletteAction: PaletteAction,
) : RecyclerView.Adapter<PaletteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PaletteViewHolder {
        val binding =
            ItemPaletteColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaletteViewHolder(binding)
    }

    override fun getItemCount(): Int = colorRes.size

    override fun onBindViewHolder(
        holder: PaletteViewHolder,
        position: Int,
    ) {
        holder.bind(colorRes[position], paletteAction)
    }
}
