package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.annotation.ColorInt

class DrawingTool(
    private val canvasDrawble: CanvasDrawble = PenDrawble(),
    private val paint: Paint = Paint().applyPaintSetting(),
) {
    fun movePath(x: Float, y: Float) {
        canvasDrawble.movePath(x, y)
    }

    fun initPath(x: Float, y: Float) {
        canvasDrawble.initPath(x, y)
    }

    fun draw(canvas: Canvas) {
        canvasDrawble.draw(canvas, paint)
    }

    fun setStrokeWidth(value: Float) {
        paint.strokeWidth = value
    }

    fun setColor(@ColorInt color: Int) {
        paint.color = color
    }

    fun newDrawingPainting(): DrawingTool =
        DrawingTool(canvasDrawble.newPainting(), Paint(paint))

    fun setPainting(brush: BrushTool): DrawingTool {
        val newPainting = canvasDrawble.from(brush)
        when (brush) {
            BrushTool.PEN -> setPenPaint()
            BrushTool.CIRCLE, BrushTool.RECTANGLE -> setShapePaint()
            BrushTool.ERASER -> setEraserPaint()
        }
        return DrawingTool(newPainting, Paint(paint))
    }

    private fun setEraserPaint() {
        paint.apply {
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.SQUARE
        }
    }

    private fun setShapePaint() {
        paint.apply {
            style = Paint.Style.FILL
            xfermode = null
        }
    }

    private fun setPenPaint() {
        paint.apply {
            xfermode = null
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
    }

    companion object {
        fun Paint.applyPaintSetting(
            @ColorInt paintColor: Int = Color.RED,
            paintWidth: Float = 50.0f,
            paintStyle: Paint.Style = Paint.Style.STROKE,
            paintCap: Paint.Cap = Paint.Cap.ROUND,
        ): Paint = this.apply {
            isAntiAlias = true
            xfermode = null
            strokeWidth = paintWidth
            strokeJoin = Paint.Join.ROUND
            style = paintStyle
            strokeCap = paintCap
            color = paintColor
        }
    }
}
