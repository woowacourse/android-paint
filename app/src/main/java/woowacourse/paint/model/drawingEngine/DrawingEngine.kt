package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas

sealed interface DrawingEngine {

    fun draw(canvas: Canvas)

    fun move(pointX: Float, pointY: Float)
}
