package woowacourse.paint.custom.view.model

import android.graphics.Paint
import android.graphics.RectF

data class Rectangle(val rectF: RectF, val paint: Paint) : Drawable {

    fun setStartPoint(startX: Float, startY: Float): Rectangle {
        return this.copy(rectF = RectF(startX, startY, startX, startY))
    }

    fun setEndPoint(endX: Float, endY: Float): Rectangle {
        return this.copy(
            rectF = rectF.apply {
                right = endX
                bottom = endY
            },
        )
    }
}
