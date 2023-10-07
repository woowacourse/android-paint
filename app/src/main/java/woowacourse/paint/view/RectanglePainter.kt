package woowacourse.paint.view

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

data class RectanglePainter(
    private val shape: PaletteShape,
    private val paint: Paint = DEFAULT_PAINT,
) : Painter {
    private var startX: Float = -1F
    private var startY: Float = -1F
    private val rect = RectF()

    override fun setPaletteColor(paletteColor: PaletteColor): Painter = RectanglePainter(
        shape = shape,
        paint = updatePaint(paintColor = paletteColor.color),
    )

    override fun setThickness(thickness: Float): RectanglePainter = RectanglePainter(
        shape = shape,
        paint = updatePaint(thickness = thickness),
    )

    private fun updatePaint(
        paintColor: Int = paint.color,
        thickness: Float = paint.strokeWidth,
    ): Paint = DEFAULT_PAINT.apply {
        color = paintColor
        strokeWidth = thickness
    }

    override fun onActionDown(x: Float, y: Float) {
        startX = x
        startY = y
        rect.set(startX, startY, x, y)
    }

    override fun onActionMove(x: Float, y: Float) {
        rect.set(startX, startY, x, y)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }

    override fun extract(): Painter = copy()

    companion object {
        private val DEFAULT_PAINT: Paint
            get() = Paint().softPainter(paintStyle = Paint.Style.FILL)
    }
}
