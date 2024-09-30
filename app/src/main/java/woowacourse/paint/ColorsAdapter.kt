package woowacourse.paint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColorsAdapter(
    private val values: List<ColorUiModel>
) : RecyclerView.Adapter<ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder =
        ColorViewHolder.from(parent)

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

}

class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val colorView: View by lazy { itemView.findViewById(R.id.iv_item_color) }

    fun bind(item: ColorUiModel) {
        colorView.setBackgroundResource(item.color)
    }

    companion object {
        fun from(parent: ViewGroup): ColorViewHolder =
            ColorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, parent, false))
    }
}

data class ColorUiModel(
    val id: Int,
    val color: Int
)
