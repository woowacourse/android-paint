package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorBinding

class PaletteAdapter(private val items: List<PaletteColor>, private val listener: PaletteListener) :
    RecyclerView.Adapter<PaletteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PaletteViewHolder {
        return PaletteViewHolder(
            ItemColorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(
        holder: PaletteViewHolder,
        position: Int,
    ) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size
}
