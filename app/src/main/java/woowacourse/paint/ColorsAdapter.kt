package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColorsAdapter(
    private val colors: List<ColorUiModel>,
    private val listener: (ColorUiModel) -> Unit,
) : RecyclerView.Adapter<ColorViewHolder>() {

    init {
        setDefaultColor()
    }

    private fun setDefaultColor() {
        listener(ColorUiModel.DEFAULT_COLOR)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ColorViewHolder = ColorViewHolder.from(parent, listener)

    override fun onBindViewHolder(
        holder: ColorViewHolder,
        position: Int,
    ) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int = colors.size

}
