package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas
import android.graphics.RectF

sealed interface ShapeDrawingEngin : DrawingEngine {

    val rectF: RectF

    override fun draw(canvas: Canvas)

    override fun draw(pointX: Float, pointY: Float)

    fun changePosition(left: Float? = null, top: Float? = null, right: Float? = null, bottom: Float? = null)
}
