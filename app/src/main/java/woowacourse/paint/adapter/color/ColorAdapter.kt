package woowacourse.paint.adapter.color

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.model.Color
import woowacourse.paint.model.Color.BLACK
import woowacourse.paint.model.Color.BLUE
import woowacourse.paint.model.Color.GREEN
import woowacourse.paint.model.Color.ORANGE
import woowacourse.paint.model.Color.RED
import woowacourse.paint.model.Color.YELLOW

class ColorAdapter(
    private val onPaletteClick: (color: Int) -> Unit,
) : RecyclerView.Adapter<ColorViewHolder>() {

    private val colors: List<Color> = listOf(RED, ORANGE, YELLOW, GREEN, BLUE, BLACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder =
        ColorViewHolder.from(parent, onPaletteClick)

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }
}

