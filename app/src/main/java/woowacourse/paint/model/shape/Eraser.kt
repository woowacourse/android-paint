package woowacourse.paint.model.shape

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

data class Eraser(
    val path: Path,
    val paint: Paint,
) : Shape {
    constructor(paint: Paint) : this(Path(), paint)
    constructor() : this(Path(), Paint())

    init {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    companion object {
        var lastX: Float = 0f
            private set
        var lastY: Float = 0f
            private set

        fun updateLastPoint(x: Float, y: Float) {
            lastX = x
            lastY = y
        }
    }
}
