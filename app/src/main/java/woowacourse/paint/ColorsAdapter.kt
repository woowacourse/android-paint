package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.model.ColorUiModel

class ColorsAdapter(private val onItemClick: (ColorUiModel) -> Unit) :
    ListAdapter<ColorUiModel, ColorViewHolder>(
        diffUtil,
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder.create(parent, onItemClick)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ColorUiModel>() {
            override fun areItemsTheSame(oldItem: ColorUiModel, newItem: ColorUiModel): Boolean {
                return oldItem.color == newItem.color
            }

            override fun areContentsTheSame(oldItem: ColorUiModel, newItem: ColorUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
