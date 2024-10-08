package woowacourse.paint.brush.draw

import android.graphics.Paint

interface Draw {
    val paint: Paint

    fun drawing(
        x: Float,
        y: Float,
    )
}
