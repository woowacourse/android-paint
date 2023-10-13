package woowacourse.paint.adapter

import android.view.ViewGroup
import androidx.annotation.ArrayRes
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(
    @ArrayRes private val colors: IntArray,
    private val onColorClicked: (Int) -> Unit,
) : RecyclerView.Adapter<ColorViewHolder>() {
    private var selectedColorIdx: Int = DEFAULT_COLOR_IDX

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(parent, onColorClicked, selectedColorIdx)
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    fun restoreSelectedColor(idx: Int) {
        selectedColorIdx = idx
    }

    companion object {
        private const val DEFAULT_COLOR_IDX = 0
    }
}
