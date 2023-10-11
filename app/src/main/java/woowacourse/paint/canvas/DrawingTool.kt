package woowacourse.paint.canvas

import android.graphics.Paint
import woowacourse.paint.canvas.drawing.Circle
import woowacourse.paint.canvas.drawing.Drawing
import woowacourse.paint.canvas.drawing.Line
import woowacourse.paint.canvas.drawing.Rectangle

enum class DrawingTool(val hasWidth: Boolean, val hasColor: Boolean) {
    PEN(true, true) {
        override fun draw(paint: Paint, invalidate: () -> Unit): Drawing =
            Line(paint, invalidate)
    },
    RECTANGLE(false, true) {
        override fun draw(paint: Paint, invalidate: () -> Unit): Drawing =
            Rectangle(paint, invalidate)
    },
    CIRCLE(false, true) {
        override fun draw(paint: Paint, invalidate: () -> Unit): Drawing =
            Circle(paint, invalidate)
    },
    ERASER(true, false) {
        override fun draw(paint: Paint, invalidate: () -> Unit): Drawing =
            Line(paint, invalidate)
    }, ;

    abstract fun draw(paint: Paint, invalidate: () -> Unit): Drawing
}
