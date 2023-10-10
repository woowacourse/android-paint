package woowacourse.paint.model.shape

import android.graphics.Paint
import android.graphics.RectF

data class Oval(
    val paint: Paint = Paint(),
) : Shape {

    val rectF: RectF = RectF()

    fun updatePosition(
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
