package woowacourse.paint.ui.main.brush

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.model.BrushTypeItem

class BrushTypeAdapter(
    private val onTypeClick: (BrushTypeItem) -> Unit,
) : ListAdapter<BrushTypeItem, BrushTypeViewHolder>(brushTypeDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushTypeViewHolder {
        return BrushTypeViewHolder.create(parent, onTypeClick)
    }

    override fun onBindViewHolder(holder: BrushTypeViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun changeTypeList(colors: List<BrushTypeItem>) {
        submitList(colors)
    }

    companion object {
        private val brushTypeDiffUtil = object : DiffUtil.ItemCallback<BrushTypeItem>() {
            override fun areItemsTheSame(
                oldItem: BrushTypeItem,
                newItem: BrushTypeItem,
            ): Boolean {
                return oldItem.brushType == newItem.brushType
            }

            override fun areContentsTheSame(
                oldItem: BrushTypeItem,
                newItem: BrushTypeItem,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
