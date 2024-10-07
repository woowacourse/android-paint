package woowacourse.paint

import android.content.Context
import android.util.AttributeSet

class RectangleView(
    context: Context,
    attrs: AttributeSet,
) : DiagramView(context, attrs) {
    override fun drawDiagram(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
    ) {
        path.moveTo(startX, startY)
        path.lineTo(endX, startY)
        path.lineTo(endX, endY)
        path.lineTo(startX, endY)
        path.lineTo(startX, startY)
    }
}
