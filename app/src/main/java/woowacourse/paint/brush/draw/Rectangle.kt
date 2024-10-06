package woowacourse.paint.brush.draw

import android.graphics.Paint
import android.graphics.RectF

class Rectangle(
    val recF: RectF = RectF(),
    val paint: Paint = Paint(),
) : Draw {
    override fun drawing(x: Float, y: Float) {
        recF.apply {
            right = x
            bottom = y
        }
    }
}
