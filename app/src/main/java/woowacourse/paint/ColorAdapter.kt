package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.Color.BLACK
import woowacourse.paint.Color.BLUE
import woowacourse.paint.Color.GREEN
import woowacourse.paint.Color.ORANGE
import woowacourse.paint.Color.RED
import woowacourse.paint.Color.YELLOW

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

