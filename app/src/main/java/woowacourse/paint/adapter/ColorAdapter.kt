package woowacourse.paint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.PaintColor
import woowacourse.paint.R
import woowacourse.paint.databinding.ItemColorButtonBinding

class ColorAdapter(private val handler: ColorHandler) : RecyclerView.Adapter<ColorViewHolder>() {
    private var color: List<PaintColor> = defaultColors

    fun submitList(newColors: List<PaintColor>) {
        color = newColors
        notifyItemRangeChanged(0, color.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemColorButtonBinding.inflate(inflater, parent, false)
        binding.handler = handler
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ColorViewHolder,
        position: Int,
    ) {
        holder.bind(color[position])
    }

    override fun getItemCount(): Int = color.size

    companion object {
        private val defaultColors =
            listOf(
                PaintColor("red", R.color.red),
                PaintColor("orange", R.color.orange),
                PaintColor("yellow", R.color.yellow),
                PaintColor("green", R.color.green),
                PaintColor("blue", R.color.blue),
                PaintColor("indigo", R.color.indigo),
                PaintColor("purple", R.color.purple),
                PaintColor("white", R.color.white),
                PaintColor("black", R.color.black),
            )
    }
}
