package woowacourse.paint.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorBinding
import woowacourse.paint.model.ColorItem
import woowacourse.paint.view.listener.ColorSelectionChangeListener

class ColorSelectionAdapter(
    private val colors: List<ColorItem>,
    private val colorSelectionChangeListener: ColorSelectionChangeListener,
) : RecyclerView.Adapter<ColorSelectionAdapter.ColorSelectionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ColorSelectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemColorBinding.inflate(layoutInflater, parent, false)
        return ColorSelectionViewHolder(binding, colorSelectionChangeListener)
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
        colorSelectionChangeListener: ColorSelectionChangeListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.colorItemSelectionChangeListener = colorSelectionChangeListener
        }

        fun bind(colorItem: ColorItem) {
            binding.colorItem = colorItem
        }
    }
}
