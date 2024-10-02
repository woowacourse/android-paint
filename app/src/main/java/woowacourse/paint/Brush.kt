package woowacourse.paint

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import kotlin.math.pow
import kotlin.math.sqrt

data class Brush(
    private val path: Path = Path(),
    val paint: Paint = Paint(),
    var brushState: BrushState = BrushState.PEN,
) {
    init {
        setUpPaintStyle()
        paint.strokeCap = Paint.Cap.ROUND
        paint.isAntiAlias = true
    }

    fun moveTo(
        x: Float,
        y: Float,
    ) {
        path.moveTo(x, y)
    }

    private fun lineTo(
        x: Float,
        y: Float,
    ) {
        path.lineTo(x, y)
    }

    fun setColor(color: Int) {
        paint.color = color
    }

    fun setThickness(size: Float) {
        paint.strokeWidth = size
    }

    fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    fun saveMovement(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        isEnd: Boolean,
    ) {
        when (brushState) {
            BrushState.PEN -> lineTo(endX, endY)

            BrushState.RECTANGLE -> {
                if (!isEnd) resetPath()
                saveRectangleMovement(startX, startY, endX, endY)
            }

            BrushState.CIRCLE -> {
                if (!isEnd) resetPath()
                saveCircleMovement(startX, startY, endX, endY)
            }

            BrushState.ERASER -> lineTo(endX, endY)
        }
    }

    private fun saveCircleMovement(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
    ) {
        val radius = calculateRadius(startX, startY, endX, endY)
        path.addCircle(startX, startY, radius, Path.Direction.CCW)
    }

    private fun saveRectangleMovement(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
    ) {
        path.addRect(startX, startY, endX, endY, Path.Direction.CCW)
    }

    private fun calculateRadius(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
    ): Float {
        return sqrt(
            (endX - startX).toDouble().pow(2.0) + (endY - startY).toDouble().pow(2.0),
        ).toFloat()
    }

    fun setBrush(brushName: String) {
        when (BrushState.getBrushState(brushName)) {
            BrushState.PEN -> changeBrushToPen()
            BrushState.RECTANGLE -> changeBrushToRectangle()
            BrushState.CIRCLE -> changeBrushToCircle()
            BrushState.ERASER -> changeBrushToEraser()
        }
    }

    private fun setUpPaintStyle() {
        when (brushState) {
            BrushState.PEN -> paint.style = Paint.Style.STROKE
            BrushState.RECTANGLE -> paint.style = Paint.Style.FILL
            BrushState.CIRCLE -> paint.style = Paint.Style.FILL
            BrushState.ERASER -> Paint.Style.STROKE
        }
    }

    private fun changeBrushToPen() {
        paint.style = Paint.Style.STROKE
        brushState = BrushState.PEN
        paint.xfermode = null
    }

    private fun changeBrushToRectangle() {
        paint.style = Paint.Style.FILL
        brushState = BrushState.RECTANGLE
        paint.xfermode = null
    }

    private fun changeBrushToCircle() {
        paint.style = Paint.Style.FILL
        brushState = BrushState.CIRCLE
        paint.xfermode = null
    }

    private fun changeBrushToEraser() {
        paint.style = Paint.Style.STROKE
        brushState = BrushState.ERASER
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    private fun resetPath() {
        path.reset()
    }
}
