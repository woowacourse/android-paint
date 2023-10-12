package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint

interface Brush {
    val paint: Paint

    fun startDrawing(x: Float, y: Float, onSuccess: () -> Unit = {})

    fun continueDrawing(x: Float, y: Float, onSuccess: () -> Unit = {})

    fun drawOn(canvas: Canvas)
}
