package woowacourse.paint.model.shape

import android.graphics.Paint

data class Oval(
    val paint: Paint,
) : Shape {

    constructor() : this(Paint())

    var startX: Float = 0f
    var startY: Float = 0f
    var endX: Float = 0f
    var endY: Float = 0f
}
