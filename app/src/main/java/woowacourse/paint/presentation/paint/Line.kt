package woowacourse.paint.presentation.paint

import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import woowacourse.paint.presentation.palette.BrushType
import kotlin.math.max
import kotlin.math.min

class Line(
    val path: Path = Path(),
    val paint: Paint = Paint(),
    private var moveType: Int = 0,
    private var brushType: BrushType = DEFAULT_BRUSH_TYPE,
) {
    fun shouldClearLastShape(): Boolean {
        return moveType == 1 && brushType.isShape()
    }

    fun clear() {
        path.reset()
    }

    fun copy(
        path: Path = Path(this.path),
        paint: Paint = Paint(this.paint),
        moveType: Int = this.moveType,
        brushType: BrushType = this.brushType,
    ): Line {
        return Line(path, paint, moveType, brushType)
    }

    fun down(x: Float, y: Float) {
        moveType = 0
        path.moveTo(x, y)
    }

    fun move(startX: Float, startY: Float, x: Float, y: Float) {
        moveType = 1
        when (brushType) {
            BrushType.PEN -> path.lineTo(x, y)
            BrushType.RECTANGLE -> {
                val left = min(startX, x)
                val top = min(startY, y)
                val right = max(startX, x)
                val bottom = max(startY, y)
                val rect = RectF(left, top, right, bottom)
                path.addRect(rect, Path.Direction.CW)
            }

            BrushType.CIRCLE -> path.addOval(startX, startY, x, y, Path.Direction.CW)
            BrushType.ERASER -> path.lineTo(x, y)
        }
    }

    fun up() {
        moveType = 2
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

    companion object {
        private val DEFAULT_BRUSH_TYPE = BrushType.PEN
    }
}
