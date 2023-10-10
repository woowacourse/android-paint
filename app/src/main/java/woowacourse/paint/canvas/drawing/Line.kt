package woowacourse.paint.canvas.drawing

import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent

class Line private constructor(path: Path, paint: Paint) : Drawing(path, paint) {
    override fun onDraw() {
        TODO("Not yet implemented")
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }
    companion object {
        fun of(path: Path, paint: Paint) = Line(Path(path), Paint(paint))
    }
}
