package woowacourse.paint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.R
import woowacourse.paint.databinding.ItemColorBinding

class ColorViewHolder(
    private val parent: ViewGroup,
    private val onColorClicked: (Int) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false),
) {
    private val binding: ItemColorBinding = ItemColorBinding.bind(itemView)

    init {
        setupColorSetting()
        setupOnColorClick()
    }

    private fun setupColorSetting() {
        val defaultColorView = parent.getChildAt(DEFAULT_COLOR_IDX)
        defaultColorView?.alpha = ALPHA_SELECTED
    }

    private fun setupOnColorClick() {
        itemView.setOnClickListener { currentView ->
            onColorClicked(adapterPosition)
            parent.forEach { it.alpha = ALPHA_UNSELECTED }
            currentView.alpha = ALPHA_SELECTED
        }
    }

    fun bind(item: Int) {
        binding.color = item
    }

    companion object {
        private const val ALPHA_UNSELECTED = 1.0f
        private const val ALPHA_SELECTED = 0.5f
        private const val DEFAULT_COLOR_IDX = 0
    }
}
