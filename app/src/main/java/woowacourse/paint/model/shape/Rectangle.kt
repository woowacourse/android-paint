package woowacourse.paint.model.shape

import android.graphics.Paint

data class Rectangle(
    val paint: Paint = Paint(),
) : Shape {

    var startX: Float = 0f
    var startY: Float = 0f
    var endX: Float = 0f
    var endY: Float = 0f
}
