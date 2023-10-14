package woowacourse.paint.custom.view.model

import android.graphics.Canvas
import android.graphics.Paint

interface Drawable {
    fun draw(canvas: Canvas)

    fun startDrawing(x: Float, y: Float, paint: Paint): Drawable

    fun keepDrawing(x: Float, y: Float)
}
