package woowacourse.paint.canvas.drawing

import android.graphics.Paint
import android.graphics.Path

class Line private constructor(path: Path, paint: Paint) : Drawing(path, paint) {
    companion object {
        fun of(path: Path, paint: Paint) = Line(Path(path), Paint(paint))
    }
}
