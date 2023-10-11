package woowacourse.paint.ui.model

import android.graphics.Paint
import androidx.annotation.DrawableRes
import woowacourse.paint.R
import woowacourse.paint.ui.glocanvas.drawing.Circle
import woowacourse.paint.ui.glocanvas.drawing.Drawing
import woowacourse.paint.ui.glocanvas.drawing.DrawingPath
import woowacourse.paint.ui.glocanvas.drawing.Rectangle
import woowacourse.paint.ui.glocanvas.paint.DrawingPaint
import woowacourse.paint.ui.glocanvas.paint.EraserPaint
import woowacourse.paint.ui.glocanvas.paint.HighlighterPaint
import woowacourse.paint.ui.glocanvas.paint.PenPaint
import woowacourse.paint.ui.glocanvas.paint.ShapePaint

enum class DrawingToolModel(@DrawableRes val image: Int, val drawingPaint: DrawingPaint) {
    PEN(R.drawable.ic_pen_100, PenPaint) {
        override fun createDrawing(paint: Paint, invalidate: () -> Unit): Drawing {
            return DrawingPath(paint, invalidate)
        }
    },
    HIGHLIGHTER(R.drawable.ic_highlighter_100, HighlighterPaint) {
        override fun createDrawing(paint: Paint, invalidate: () -> Unit): Drawing {
            return DrawingPath(paint, invalidate)
        }
    },
    CIRCLE(R.drawable.ic_circle_100, ShapePaint) {
        override fun createDrawing(paint: Paint, invalidate: () -> Unit): Drawing {
            return Circle(paint, invalidate)
        }
    },
    RECTANGLE(R.drawable.ic_rectangle_100, ShapePaint) {
        override fun createDrawing(paint: Paint, invalidate: () -> Unit): Drawing {
            return Rectangle(paint, invalidate)
        }
    },
    ERASER(R.drawable.ic_eraser_100, EraserPaint) {
        override fun createDrawing(paint: Paint, invalidate: () -> Unit): Drawing {
            return DrawingPath(paint, invalidate)
        }
    }, ;

    abstract fun createDrawing(paint: Paint, invalidate: () -> Unit): Drawing
}
