package woowacourse.paint.paintcanvas.shape

import android.graphics.Canvas
import android.graphics.Paint

interface Shape {
    fun onActionDown(
        pointX: Float,
        pointY: Float,
    )

    fun onActionMove(
        pointX: Float,
        pointY: Float,
    )

    fun onActionUp(
        pointX: Float,
        pointY: Float,
    )

    fun draw(
        canvas: Canvas,
        paint: Paint,
    )
}
