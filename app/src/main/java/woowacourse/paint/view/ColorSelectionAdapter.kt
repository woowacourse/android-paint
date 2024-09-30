package woowacourse.paint.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorBinding
import woowacourse.paint.model.ColorItem
import woowacourse.paint.view.listener.ColorSelectionListener

class ColorSelectionAdapter(
    private val colors: List<ColorItem>,
    private val colorSelectionListener: ColorSelectionListener,
) : RecyclerView.Adapter<ColorSelectionAdapter.ColorSelectionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ColorSelectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemColorBinding.inflate(layoutInflater, parent, false)
        return ColorSelectionViewHolder(binding, colorSelectionListener)
    }

    override fun onBindViewHolder(
        holder: ColorSelectionViewHolder,
        position: Int,
    ) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int = colors.size

    class ColorSelectionViewHolder(
        private val binding: ItemColorBinding,
        colorSelectionListener: ColorSelectionListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.colorItemSelectionListener = colorSelectionListener
        }

        fun bind(colorItem: ColorItem) {
            binding.colorItem = colorItem
        }
    }
}
