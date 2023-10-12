package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

data class RectangleDrawingEngine(
    override val paint: Paint = Paint(),
    override val rectF: RectF = RectF(),
) : ShapeDrawingEngin {

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rectF, paint)
    }

    override fun draw(pointX: Float, pointY: Float) {
        changePosition(right = pointX, bottom = pointY)
    }

    override fun changePosition(
        left: Float?,
        top: Float?,
        right: Float?,
        bottom: Float?,
    ) {
        rectF.apply {
            if (left != null) this.left = left
            if (top != null) this.top = top
            if (right != null) this.right = right
            if (bottom != null) this.bottom = bottom
        }
    }
}
