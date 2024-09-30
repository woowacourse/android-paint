package woowacourse.paint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView

class ColorsAdapter(
    private val values: List<ColorUiModel>,
    private val listener: (ColorUiModel) -> Unit
) : RecyclerView.Adapter<ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder =
        ColorViewHolder.from(parent, listener)

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int = values.size
}

class ColorViewHolder(
    itemView: View,
    private val listener: (ColorUiModel) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val colorView: View by lazy { itemView.findViewById(R.id.iv_item_color) }

    fun bind(item: ColorUiModel) {
        colorView.setBackgroundColor(item.color)
        colorView.setOnClickListener {
            listener(item)
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: (ColorUiModel) -> Unit): ColorViewHolder =
            ColorViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, parent, false),
                listener
            )
    }
}

data class ColorUiModel(
    val id: Int,
    @ColorInt val color: Int
)
