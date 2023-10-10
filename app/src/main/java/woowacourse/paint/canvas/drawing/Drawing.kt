package woowacourse.paint.canvas.drawing

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.canvas.Tool

abstract class Drawing(val path: Path, val paint: Paint) {
    companion object {
        fun of(path: Path, paint: Paint, tool: Tool): Drawing {
            return when (tool) {
                Tool.PEN, Tool.ERASER -> Line.of(path, paint)
                Tool.RECTANGLE -> Rectangle.of(path, paint)
                Tool.CIRCLE -> Circle.of(path, paint)
            }
        }
    }
}
