package woowacourse.paint.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

interface DrawingShape {
    fun drawShapeOnCanvas(canvas: Canvas, paint: Paint)
    fun addShapeToPath(path: Path)
}
