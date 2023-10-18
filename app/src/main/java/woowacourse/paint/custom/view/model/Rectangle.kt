package woowacourse.paint.custom.view.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

data class Rectangle(val rectF: RectF, val paint: Paint) : Drawable {

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rectF, paint)
    }

    override fun keepDrawing(x: Float, y: Float) {
        rectF.apply {
            right = x
            bottom = y
        }
    }
}
