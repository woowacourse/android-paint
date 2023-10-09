package woowacourse.paint.board.draw

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent

sealed class GraphicObject {
    abstract val paint: Paint

    init {
        assertIsRegisteredGraphicObjectType()
    }

    private fun assertIsRegisteredGraphicObjectType() {
        val graphicObjectType: GraphicObjectType? =
            GraphicObjectType.getByJavaClassName(this.javaClass.name)
        GraphicObjectType.values().contains(graphicObjectType)
    }

    abstract fun onTouchEventAction(event: MotionEvent): Boolean
    abstract fun onDrawAction(canvas: Canvas)
}
