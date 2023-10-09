package woowacourse.paint.canvas

import android.graphics.Paint
import android.graphics.Path

class Drawing private constructor(val path: Path, val paint: Paint) {
    companion object {
        fun of(path: Path, paint: Paint) = Drawing(Path(path), Paint(paint))
    }
}
