package woowacourse.paint.canvas

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.canvas.drawing.Circle
import woowacourse.paint.canvas.drawing.Drawing
import woowacourse.paint.canvas.drawing.Line
import woowacourse.paint.canvas.drawing.Rectangle

enum class Tool {
    PEN {
        override fun draw(path: Path, paint: Paint): Drawing = Line.of(path, paint)
    },
    RECTANGLE {
        override fun draw(path: Path, paint: Paint): Drawing = Rectangle.of(path, paint)
    },
    CIRCLE {
        override fun draw(path: Path, paint: Paint): Drawing = Circle.of(path, paint)
    },
    ERASER {
        override fun draw(path: Path, paint: Paint): Drawing = Line.of(path, paint)
    }, ;

    abstract fun draw(path: Path, paint: Paint): Drawing
}
