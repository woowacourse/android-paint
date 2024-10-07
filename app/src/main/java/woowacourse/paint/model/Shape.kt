package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint

interface Shape {
    fun draw(
        canvas: Canvas,
        paint: Paint,
    )

    fun update(
        x: Float,
        y: Float,
    )
}
