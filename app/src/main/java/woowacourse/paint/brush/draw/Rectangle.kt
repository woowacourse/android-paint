package woowacourse.paint.brush.draw

import android.graphics.Paint
import android.graphics.RectF

class Rectangle(
    val recF: RectF = RectF(),
    val paint: Paint = Paint(),
) {

    fun drawRectangle(x: Float, y: Float){
        recF.apply {
            right = x
            bottom = y
        }
    }
}
