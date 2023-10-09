package woowacourse.paint.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.ui.model.PaintModeModel

class PaintModeAdapter(
    private val paintModes: List<PaintModeModel>,
    private val onItemClick: (PaintModeModel) -> Unit
) : RecyclerView.Adapter<PaintModeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaintModeViewHolder {
        return PaintModeViewHolder.create(
            parent
        ) { position -> onItemClick(paintModes[position]) }
    }

    override fun getItemCount(): Int = paintModes.size

    override fun onBindViewHolder(holder: PaintModeViewHolder, position: Int) {
        holder.bind(paintModes[position])
    }
}
