package woowacourse.paint.drawing

import android.graphics.Canvas
import android.graphics.Paint

sealed interface Drawing {
    fun drawOn(canvas: Canvas)

    fun setStartPoint(
        x: Float,
        y: Float,
    )

    fun pathLineTo(
        x: Float,
        y: Float,
    )

    fun copyWithPaint(thickness: Float): Drawing

    fun copyWithPaint(color: Int): Drawing

    fun copyPoint(
        pointX: Float,
        pointY: Float,
    ): Drawing

    companion object {
        val DEFAULT_BRUSH_STYLE = Paint.Style.STROKE
        val DEFAULT_BRUSH_CAP = Paint.Cap.ROUND
    }
}
