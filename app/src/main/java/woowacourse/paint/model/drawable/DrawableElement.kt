package woowacourse.paint.model.drawable

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt

interface DrawableElement {
    val paint: Paint
    fun drawCurrent(canvas: Canvas)
    fun startDrawing(x: Float, y: Float): DrawableElement
    fun keepDrawing(x: Float, y: Float)
    fun changePaintColor(@ColorInt color: Int): DrawableElement
}
