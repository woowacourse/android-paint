package woowacourse.paint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.databinding.ItemDiagramBinding
import woowacourse.paint.paintcanvas.Diagram

class DiagramAdapter(private val handler: PaintCanvasHandler) :
    RecyclerView.Adapter<DiagramViewHolder>() {
    private var diagrams: List<Diagram> = defaultDiagrams

    fun submitList(newDiagrams: List<Diagram>) {
        diagrams = newDiagrams
        notifyItemRangeChanged(0, diagrams.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DiagramViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDiagramBinding.inflate(inflater, parent, false)
        binding.handler = handler
        return DiagramViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DiagramViewHolder,
        position: Int,
    ) {
        holder.bind(diagrams[position])
    }

    override fun getItemCount(): Int = diagrams.size

    companion object {
        private val defaultDiagrams =
            listOf(
                Diagram.PEN,
                Diagram.RECT,
                Diagram.OVAL,
            )
    }
}
