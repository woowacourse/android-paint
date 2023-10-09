package woowacourse.paint.paintBoard.tools

import android.graphics.Path
import woowacourse.paint.paintBoard.Line
import kotlin.math.sqrt

class CirclePen(
    override val onSave: (line: Line) -> Unit,
    override val line: Line
) : ShapePen(onSave) {

    override fun draw(pointX: Float, pointY: Float) {
        line.path.addCircle(pointX, pointY, getRadius(pointX, pointY), Path.Direction.CCW)
    }

    private fun getRadius(pointX: Float, pointY: Float): Float {
        val dx = pointX - startingPointX
        val dy = pointY - startingPointY

        return sqrt((dx * dx + dy * dy))
    }
}
