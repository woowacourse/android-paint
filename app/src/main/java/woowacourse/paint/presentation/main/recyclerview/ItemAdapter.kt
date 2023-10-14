package woowacourse.paint.presentation.main.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.presentation.uimodel.BrushColorUiModel
import woowacourse.paint.presentation.uimodel.BrushToolView
import woowacourse.paint.presentation.uimodel.BrushTypeUiModel

class ItemAdapter(private val brushToolViews: List<BrushToolView>, private val onClick: (BrushToolView) -> Unit) :
    RecyclerView.Adapter<BrushToolViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushToolViewHolder {
        return BrushToolViewHolder(parent) { onClick(brushToolViews[it]) }
    }

    override fun getItemCount(): Int {
        return brushToolViews.size
    }

    override fun onBindViewHolder(holder: BrushToolViewHolder, position: Int) {
        when (brushToolViews[position]) {
            is BrushColorUiModel -> holder.bind(brushToolViews[position] as BrushColorUiModel)
            is BrushTypeUiModel -> holder.bind(brushToolViews[position] as BrushTypeUiModel)
        }
    }
}
