package woowacourse.paint

import android.content.Context
import android.util.AttributeSet

class TriangleView(
    context: Context,
    attrs: AttributeSet,
) : DiagramView(context, attrs) {
    override fun drawDiagram(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
    ) {
        path.moveTo((startX + endX) / 2, startY)
        path.lineTo(startX, endY)
        path.lineTo(endX, endY)
        path.lineTo((startX + endX) / 2, startY)
    }
}
