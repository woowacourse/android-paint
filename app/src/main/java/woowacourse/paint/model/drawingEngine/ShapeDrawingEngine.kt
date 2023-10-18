package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

abstract class ShapeDrawingEngine : DrawingEngine {

    abstract override val paint: Paint
    abstract val rectF: RectF

    abstract override fun draw(canvas: Canvas)

    override fun setStartPoint(pointX: Float, pointY: Float) {
        changePosition(left = pointX, top = pointY)
    }

    override fun setEndPoint(pointX: Float, pointY: Float) {
        changePosition(right = pointX, bottom = pointY)
    }

    fun changePosition(
        left: Float? = null,
        top: Float? = null,
        right: Float? = null,
        bottom: Float? = null,
    ) {
        rectF.apply {
            if (left != null) this.left = left
            if (top != null) this.top = top
            if (right != null) this.right = right
            if (bottom != null) this.bottom = bottom
        }
    }
}
