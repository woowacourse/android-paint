package woowacourse.paint.custom.view.model

import android.graphics.Canvas

interface Drawable {
    fun draw(canvas: Canvas)

    fun keepDrawing(x: Float, y: Float)
}
