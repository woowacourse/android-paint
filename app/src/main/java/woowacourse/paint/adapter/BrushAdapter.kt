package woowacourse.paint.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import woowacourse.paint.listener.OnBrushClickListener
import woowacourse.paint.model.BrushBox
import woowacourse.paint.viewholder.BrushViewHolder

class BrushAdapter(
    private val onBrushClickListener: OnBrushClickListener,
) : ListAdapter<BrushBox, BrushViewHolder>(BrushDiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushViewHolder {
        return BrushViewHolder.from(parent, onBrushClickListener)
    }

    override fun onBindViewHolder(holder: BrushViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }
}
