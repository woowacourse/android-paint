package woowacourse.paint.tool

import android.graphics.Canvas
import android.graphics.Paint

interface Tool {
    fun copy(): Tool
    fun startDraw(pointX: Float, pointY: Float)
    fun onDraw(pointX: Float, pointY: Float)
    fun drawPath(canvas: Canvas, paint: Paint)
}
