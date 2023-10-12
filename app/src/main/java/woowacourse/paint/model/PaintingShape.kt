package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint

interface PaintingShape {
    fun initPath(x: Float, y: Float)
    fun movePath(x: Float, y: Float)
    fun draw(canvas: Canvas, paint: Paint)

    fun newPaintingShape(): PaintingShape
}
