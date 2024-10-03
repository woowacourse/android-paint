package woowacourse.paint.tools

import android.graphics.Canvas
import android.graphics.Paint

sealed interface DrawingTool {
    fun setStartPoint(
        x: Float,
        y: Float,
        newPaint: Paint,
    )

    fun draw(
        x: Float,
        y: Float,
    )

    fun renderOnCanvas(canvas: Canvas)
}
