package woowacourse.paint.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemBrushButtonBinding
import woowacourse.paint.model.Brush
import woowacourse.paint.view.listener.BrushChangeListener

class BrushControllerAdapter(
    private val brushes: List<Brush>,
    private val brushChangeListener: BrushChangeListener,
) : RecyclerView.Adapter<BrushControllerAdapter.BrushViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BrushViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBrushButtonBinding.inflate(layoutInflater, parent, false)
        return BrushViewHolder(binding, brushChangeListener)
    }

    override fun onBindViewHolder(
        holder: BrushViewHolder,
        position: Int,
    ) {
        holder.bind(brushes[position])
    }

    override fun getItemCount(): Int = brushes.size

    class BrushViewHolder(
        private val binding: ItemBrushButtonBinding,
        brushChangeListener: BrushChangeListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.brushChangeListener = brushChangeListener
        }

        fun bind(brush: Brush) {
            binding.brush = brush
        }
    }
}
