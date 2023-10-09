package woowacourse.paint.view.canvas.painter

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import woowacourse.paint.common.softPainter
import woowacourse.paint.view.palette.color.PaletteColor
import woowacourse.paint.view.palette.shape.PaletteShape

data class RectanglePainter(
    private val shape: PaletteShape,
    private val paletteColor: PaletteColor = PaletteColor.values().first(),
) : Painter(getDefaultPaint(paletteColor)) {
    private var startX: Float = -1F
    private var startY: Float = -1F
    private val rect = RectF()

    override fun setPaletteColor(paletteColor: PaletteColor): Painter = copy(
        paletteColor = paletteColor,
    )

    override fun setThickness(thickness: Float): RectanglePainter = copy()

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
        private fun getDefaultPaint(
            paletteColor: PaletteColor = PaletteColor.values().first(),
        ): Paint = Paint().softPainter(
            paletteColor = paletteColor,
            paintStyle = Paint.Style.FILL,
        )
    }
}
