package woowacourse.paint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemColorBinding

class ColorViewHolder(
    private val binding: ItemColorBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(color: Int) {
        binding.color = color
    }

    companion object {

        fun of(
            parent: ViewGroup,
            onClicked: (color: Int) -> Unit
        ): ColorViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemColorBinding.inflate(layoutInflater, parent, false).apply {
                root.setOnClickListener {
                    onClicked(color)
                }
            }

            return ColorViewHolder(binding)
        }
    }
}
