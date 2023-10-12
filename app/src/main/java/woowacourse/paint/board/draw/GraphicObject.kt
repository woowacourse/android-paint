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
            GraphicObjectType.getByJavaClassName(this.javaClass.simpleName)
        assert(GraphicObjectType.values().contains(graphicObjectType)) {
            GRAPHIC_OBJECT_TYPE_REGISTER_ERROR
        }
    }

    abstract fun onTouchEventAction(event: MotionEvent): Boolean
    abstract fun onDrawAction(canvas: Canvas)

    companion object {
        private const val GRAPHIC_OBJECT_TYPE_REGISTER_ERROR =
            "그래픽 요소의 enum값을 등록하지 않았거나 javaNameClass의 명이 틀렸습니다."
    }
}
