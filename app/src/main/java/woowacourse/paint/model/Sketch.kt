package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint

abstract class Sketch(currentColor: Int, currentWidth: Float) {
    abstract fun draw(canvas: Canvas)

    protected val paint =
        Paint().apply {
            color = currentColor
            style = Paint.Style.STROKE
            strokeWidth = currentWidth
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }

    abstract fun isTouched(
        x: Float,
        y: Float,
    ): Boolean
}
