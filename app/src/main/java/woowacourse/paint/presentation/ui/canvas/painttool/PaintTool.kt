package woowacourse.paint.presentation.ui.canvas.painttool

import android.graphics.Canvas
import woowacourse.paint.presentation.ui.canvas.Palette

interface PaintTool {

    val palette: Palette

    fun draw(canvas: Canvas)

    fun nextPath(): PaintTool

    fun changePalette(palette: Palette?): PaintTool

    fun onDownEvent(pointX: Float, pointY: Float)

    fun onMoveEvent(pointX: Float, pointY: Float)
}
