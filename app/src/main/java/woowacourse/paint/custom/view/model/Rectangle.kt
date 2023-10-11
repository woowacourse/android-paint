package woowacourse.paint.custom.view.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

data class Rectangle(val rectF: RectF, val paint: Paint) : Drawable {

    fun setEndPoint(endX: Float, endY: Float): Rectangle {
        return this.copy(
            rectF = rectF.apply {
                right = endX
                bottom = endY
            },
        )
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rectF, paint)
    }
}
