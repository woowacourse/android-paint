package woowacourse.paint.presentation.paint

import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import woowacourse.paint.presentation.paint.model.BrushType
import woowacourse.paint.presentation.paint.model.TouchType
import kotlin.math.max
import kotlin.math.min

class Drawing(
    val path: Path = Path(),
    val paint: Paint = Paint(),
    private var touchType: TouchType = TouchType.DOWN,
    private var brushType: BrushType,
) {
    fun shouldClearLastShape(): Boolean {
        return touchType == TouchType.MOVE && brushType.isShape()
    }

    fun clear() {
        path.reset()
    }

    fun copy(
        path: Path = Path(this.path),
        paint: Paint = Paint(this.paint),
        moveType: TouchType = this.touchType,
        brushType: BrushType = this.brushType,
    ): Drawing {
        return Drawing(path, paint, moveType, brushType)
    }

    fun down(x: Float, y: Float) {
        touchType = TouchType.DOWN
        path.moveTo(x, y)
    }

    fun move(startX: Float, startY: Float, x: Float, y: Float) {
        touchType = TouchType.MOVE
        when (brushType) {
            BrushType.PEN, BrushType.ERASER -> path.lineTo(x, y)
            BrushType.RECTANGLE -> {
                val rect = RectF(min(startX, x), min(startY, y), max(startX, x), max(startY, y))
                path.addRect(rect, Path.Direction.CW)
            }

            BrushType.CIRCLE -> path.addOval(startX, startY, x, y, Path.Direction.CW)
        }
    }

    fun up() {
        touchType = TouchType.UP
    }

    fun changePaintColor(color: Int) {
        paint.color = color
    }

    fun changeStrokeSize(strokeSize: Float) {
        paint.strokeWidth = strokeSize
    }

    fun changeBrushType(brushType: BrushType) {
        this.brushType = brushType
        paint.xfermode = this.brushType.eraserMode()
        paint.style = this.brushType.paintStyle()
    }
}
