package woowacourse.paint

import android.graphics.Canvas
import android.graphics.Path
import woowacourse.paint.paintingtools.Eraser
import woowacourse.paint.paintingtools.PaintingTool
import woowacourse.paint.paintingtools.Pen

data class Painting(
    private val path: Path,
    private var paintingTool: PaintingTool,
) {

    fun getInitializedPathPainting(): Painting {
        return copy(
            path = Path(),
            paintingTool = paintingTool.copy(),
        )
    }

    fun movePath(x: Float, y: Float) {
        path.moveTo(x, y)
    }

    fun linePath(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    fun changeColor(color: Int) {
        paintingTool.changeColor(color)
    }

    fun changeWidth(width: Float) {
        paintingTool.changeWidth(width)
    }

    fun drawOnCanvas(canvas: Canvas) {
        canvas.drawPath(path, paintingTool.paint)
    }

    fun setEraseMode() {
        paintingTool = Eraser(paintingTool.paintColor, paintingTool.paintWidth)
    }

    fun setPenMode() {
        paintingTool = Pen(paintingTool.paintColor, paintingTool.paintWidth)
    }
}
