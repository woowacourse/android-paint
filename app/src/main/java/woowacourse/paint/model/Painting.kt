package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.annotation.ColorInt

class Painting(
    private val shape: PaintingShape = Stroke(),
    private val paint: Paint = Paint().applyPaintSetting(),
) {
    fun movePath(x: Float, y: Float) {
        shape.initPath(x, y)
    }

    fun initPath(x: Float, y: Float) {
        shape.movePath(x, y)
    }

    fun draw(canvas: Canvas) {
        shape.draw(canvas, paint)
    }

    fun setStrokeWidth(value: Float) {
        paint.strokeWidth = value
    }

    fun setColor(@ColorInt color: Int) {
        paint.color = color
    }

    fun newDrawingPainting(): Painting =
        Painting(shape.newPaintingShape(), Paint(paint))

    fun setPainting(brush: PaintBrush): Painting {
        val shape: PaintingShape = setPaintingShape(brush)
        val paint = setPaint(brush)

        return Painting(shape, paint)
    }

    private fun setPaintingShape(brush: PaintBrush): PaintingShape {
        return when (brush) {
            PaintBrush.PEN, PaintBrush.ERASER -> Stroke()
            PaintBrush.CIRCLE -> Circle()
            PaintBrush.RECTANGLE -> Rectangle()
        }
    }

    private fun setPaint(brush: PaintBrush) = Paint(
        paint.apply {
            when (brush) {
                PaintBrush.PEN -> setPenPaint()
                PaintBrush.CIRCLE, PaintBrush.RECTANGLE -> setShapePaint()
                PaintBrush.ERASER -> setEraserPaint()
            }
        },
    )

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
