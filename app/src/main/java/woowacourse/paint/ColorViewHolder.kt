package woowacourse.paint

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColorViewHolder(itemView: View, onClickListener: (Int) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    private val colorView = itemView.findViewById<View>(R.id.color)

    private var colorInt = Color.BLACK
        set(value) {
            field = value
            colorView.setBackgroundColor(value)
        }

    init {
        colorView.setOnClickListener {
            onClickListener(colorInt)
        }
    }

    fun bind(color: Int) {
        colorInt = color
    }

    companion object {
        fun from(parent: ViewGroup, onClickListener: (Int) -> Unit): ColorViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_main_colors, parent, false)
            return ColorViewHolder(view, onClickListener)
        }
    }
}
