package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas
import android.graphics.Paint

sealed interface DrawingEngine {

    val paint: Paint

    fun draw(canvas: Canvas)

    fun setStartPoint(pointX: Float, pointY: Float)

    fun setEndPoint(pointX: Float, pointY: Float)
}
