package woowacourse.paint.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

interface DrawingShape {
    fun drawShape(canvas: Canvas, paint: Paint)
    fun addShape(path: Path)
}
