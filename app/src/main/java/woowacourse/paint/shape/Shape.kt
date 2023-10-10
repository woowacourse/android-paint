package woowacourse.paint.shape

import android.graphics.Canvas
import android.graphics.Paint

interface Shape {
    fun copy(): Shape
    fun startDraw(pointX: Float, pointY: Float)
    fun onDraw(pointX: Float, pointY: Float)
    fun drawPath(canvas: Canvas, paint: Paint)
}
