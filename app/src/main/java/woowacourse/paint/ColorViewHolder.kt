package woowacourse.paint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColorViewHolder(
    itemView: View,
    private val listener: (ColorUiModel) -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    private val colorView: View by lazy { itemView.findViewById(R.id.iv_item_color) }

    fun bind(item: ColorUiModel) {
        colorView.setBackgroundColor(item.color)
        colorView.setOnClickListener {
            listener(item)
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            listener: (ColorUiModel) -> Unit,
        ): ColorViewHolder =
            ColorViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.color_item, parent, false),
                listener,
            )
    }
}
