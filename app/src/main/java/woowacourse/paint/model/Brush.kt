package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint

interface Brush {
    val paint: Paint

    fun start(x: Float, y: Float, onCommit: () -> Unit = {})

    fun move(x: Float, y: Float, onCommit: () -> Unit = {})

    fun drawOn(canvas: Canvas)

    companion object {
        fun from(shape: BrushShape): Brush = when (shape) {
            BrushShape.LINE -> BrushPen()
            BrushShape.CIRCLE -> BrushCircle()
            BrushShape.RECT -> BrushRect()
            BrushShape.ERASER -> BrushEraser()
        }
    }
}
