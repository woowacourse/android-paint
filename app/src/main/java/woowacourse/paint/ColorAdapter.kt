package woowacourse.paint

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.Color.BLACK
import woowacourse.paint.Color.BLUE
import woowacourse.paint.Color.GREEN
import woowacourse.paint.Color.ORANGE
import woowacourse.paint.Color.RED
import woowacourse.paint.Color.YELLOW

class ColorAdapter(
    private val onClick: (color: Int) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<ColorViewHolder>() {

    private val colors: List<Color> = listOf(RED, ORANGE, YELLOW, GREEN, BLUE, BLACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder =
        ColorViewHolder.from(parent, onClick, context)

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }
}

