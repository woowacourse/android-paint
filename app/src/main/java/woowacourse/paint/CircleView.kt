package woowacourse.paint

import android.content.Context
import android.graphics.Path
import android.util.AttributeSet

class CircleView(context: Context, attrs: AttributeSet) :
    DiagramView(context, attrs) {
    override fun drawDiagram(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
    ) {
        path.addOval(startX, startY, endX, endY, Path.Direction.CW)
    }
}
