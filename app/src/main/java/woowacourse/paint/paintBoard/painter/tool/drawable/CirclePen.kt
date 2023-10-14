package woowacourse.paint.paintBoard.painter.tool.drawable

import android.graphics.Path
import woowacourse.paint.paintBoard.Line
import kotlin.math.sqrt

class CirclePen(
    reset: () -> Unit,
    onSave: (line: Line) -> Unit
) : ShapePen(reset, onSave) {

    override fun draw(pointX: Float, pointY: Float) {
        line.path.addCircle(pointX, pointY, getRadius(pointX, pointY), Path.Direction.CCW)
    }

    private fun getRadius(pointX: Float, pointY: Float): Float {
        val dx = pointX - startingPointX
        val dy = pointY - startingPointY

        return sqrt((dx * dx + dy * dy))
    }
}
