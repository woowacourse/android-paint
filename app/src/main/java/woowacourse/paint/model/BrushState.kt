package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF

interface BrushState {
    fun onDraw(canvas: Canvas, currentPath: Path?, currentPaint: Paint)
    fun onTouchDown(x: Float, y: Float): Path
    fun onTouchMove(path: Path, x: Float, y: Float)
    fun onTouchUp(path: Path, paint: Paint)
}

class PenState(private val strokes: Strokes) : BrushState {
    override fun onDraw(canvas: Canvas, currentPath: Path?, currentPaint: Paint) {
        currentPath?.let { canvas.drawPath(it, currentPaint) }
    }

    override fun onTouchDown(x: Float, y: Float): Path = Path().apply { moveTo(x, y) }

    override fun onTouchMove(path: Path, x: Float, y: Float) {
        path.lineTo(x, y)
    }

    override fun onTouchUp(path: Path, paint: Paint) {
        strokes.add(Stroke(path, Paint(paint)))
    }
}

class RectangularState(private val strokes: Strokes) : BrushState {
    private var startX = 0f
    private var startY = 0f

    override fun onDraw(canvas: Canvas, currentPath: Path?, currentPaint: Paint) {
        currentPath?.let { canvas.drawPath(it, currentPaint) }
    }

    override fun onTouchDown(x: Float, y: Float): Path {
        startX = x
        startY = y
        return Path()
    }

    override fun onTouchMove(path: Path, x: Float, y: Float) {
        path.reset()
        path.addRect(startX, startY, x, y, Path.Direction.CW)
    }

    override fun onTouchUp(path: Path, paint: Paint) {
        strokes.add(Stroke(path, Paint(paint)))
    }
}

class CircleState(private val strokes: Strokes) : BrushState {
    private var startX = 0f
    private var startY = 0f

    override fun onDraw(canvas: Canvas, currentPath: Path?, currentPaint: Paint) {
        currentPath?.let { canvas.drawPath(it, currentPaint) }
    }

    override fun onTouchDown(x: Float, y: Float): Path {
        startX = x
        startY = y
        return Path()
    }

    override fun onTouchMove(path: Path, x: Float, y: Float) {
        path.reset()
        val rect = RectF(startX, startY, x, y)
        path.addOval(rect, Path.Direction.CW)
    }

    override fun onTouchUp(path: Path, paint: Paint) {
        strokes.add(Stroke(path, Paint(paint)))
    }
}

class EraserState(private val strokes: Strokes) : BrushState {
    override fun onDraw(canvas: Canvas, currentPath: Path?, currentPaint: Paint) {
        /* Eraser DOES NOT draw anything. */
    }

    override fun onTouchDown(x: Float, y: Float): Path = Path().apply { moveTo(x, y) }

    override fun onTouchMove(path: Path, x: Float, y: Float) {
        path.lineTo(x, y)
        strokes.removeIntersectingStrokes(path)
    }

    override fun onTouchUp(path: Path, paint: Paint) {
        /* ERASER DOES NOTHING WHEN TOUCHING UP */
    }
}
