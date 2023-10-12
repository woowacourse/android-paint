package woowacourse.paint.presentation.ui.canvas

import android.graphics.Canvas
import woowacourse.paint.domain.model.BrushType
import woowacourse.paint.presentation.ui.canvas.painttool.PaintTool
import woowacourse.paint.presentation.ui.canvas.painttool.PaintTools
import woowacourse.paint.presentation.ui.canvas.painttool.filling.Circle
import woowacourse.paint.presentation.ui.canvas.painttool.filling.Rectangle
import woowacourse.paint.presentation.ui.canvas.painttool.stroking.Eraser
import woowacourse.paint.presentation.ui.canvas.painttool.stroking.Line

class Painting {
    private val drawnTools: PaintTools = PaintTools()
    private var drawingTool: PaintTool = Line(Palette())

    fun drawLines(canvas: Canvas) {
        drawnTools.draw(canvas)
    }

    fun changeColor(color: Int) {
        val palette = drawingTool.palette.changeColor(color)
        changePalette(palette)
    }

    fun changeWidth(width: Float) {
        val palette = drawingTool.palette.changeWidth(width)
        changePalette(palette)
    }

    private fun changePalette(palette: Palette) {
        drawingTool = drawingTool.changePalette(palette)
    }

    fun changeType(brushType: BrushType) {
        drawingTool = when (brushType) {
            BrushType.LINE -> Line(drawingTool.palette.copy())
            BrushType.CIRCLE -> Circle(drawingTool.palette.copy())
            BrushType.RECTANGLE -> Rectangle(drawingTool.palette.copy())
            BrushType.ERASER -> Eraser(drawingTool.palette.copy())
        }
    }

    fun drawLine(pointX: Float, pointY: Float) {
        drawingTool.onMoveEvent(pointX, pointY)
    }

    fun movePoint(pointX: Float, pointY: Float) {
        drawingTool = drawingTool.nextPath()
        drawnTools.add(drawingTool)
        drawingTool.onDownEvent(pointX, pointY)
    }

    fun clear() {
        drawnTools.clear()
    }

    fun undo() {
        drawnTools.undo()
    }

    fun redo() {
        drawnTools.redo()
    }
}
