package woowacourse.paint.canvas

import android.graphics.Color

enum class PaletteColor(val colorCode: Int) {
    RED(Color.RED), ORANGE(Color.parseColor("#FF9802")), YELLOW(Color.YELLOW), GREEN(Color.GREEN), BLUE(Color.BLUE);

    companion object {
        fun getAllColors(): List<PaletteColor> = PaletteColor.values().toList()
    }
}
