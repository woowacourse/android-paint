package woowacourse.paint.model.drawingEngine

import android.graphics.RectF

sealed interface ShapeDrawingEngin : DrawingEngine {

    val rectF: RectF

    fun changePosition(left: Float? = null, top: Float? = null, right: Float? = null, bottom: Float? = null)
}
