package woowacourse.paint.model.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

interface Shape {
    fun drawShapeOnCanvas(canvas: Canvas, paint: Paint)
    fun addShapeToPath(path: Path)
}
