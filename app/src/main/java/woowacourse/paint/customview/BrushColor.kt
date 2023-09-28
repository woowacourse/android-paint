package woowacourse.paint.customview

import androidx.annotation.ColorRes
import woowacourse.paint.R
import woowacourse.paint.main.model.BrushColorBox

enum class BrushColor(@ColorRes val colorRes: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue),
    ;

    companion object {
        fun getColorBoxes(@ColorRes selectedColor: Int): List<BrushColorBox> =
            BrushColor.values().map { brushColor ->
                if (selectedColor == brushColor.colorRes) {
                    BrushColorBox(brushColor, true)
                } else {
                    BrushColorBox(brushColor, false)
                }
            }
    }
}
