package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas
import android.graphics.Paint

sealed interface DrawingEngine {

    val paint: Paint

    fun draw(canvas: Canvas)

    fun draw(pointX: Float, pointY: Float)
}
