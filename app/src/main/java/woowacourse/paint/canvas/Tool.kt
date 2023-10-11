package woowacourse.paint.canvas

import android.graphics.Paint
import woowacourse.paint.canvas.drawing.Circle
import woowacourse.paint.canvas.drawing.Drawing
import woowacourse.paint.canvas.drawing.Line
import woowacourse.paint.canvas.drawing.Rectangle

enum class Tool {
    PEN {
        override fun draw(paint: Paint, invalidate: () -> Unit): Drawing =
            Line.of(paint, invalidate)
    },
    RECTANGLE {
        override fun draw(paint: Paint, invalidate: () -> Unit): Drawing =
            Rectangle.of(paint, invalidate)
    },
    CIRCLE {
        override fun draw(paint: Paint, invalidate: () -> Unit): Drawing =
            Circle.of(paint, invalidate)
    },
    ERASER {
        override fun draw(paint: Paint, invalidate: () -> Unit): Drawing =
            Line.of(paint, invalidate)
    }, ;

    abstract fun draw(paint: Paint, invalidate: () -> Unit): Drawing
}
