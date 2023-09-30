package woowacourse.paint.customVeiw

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class Brush(private val path: Path, private val paint: Paint) {
    private var lastPosition = Pair(0F, 0F)

    fun start(x: Float, y: Float) {
        path.moveTo(x, y)
        lastPosition = Pair(x, y)
    }

    fun move(x: Float, y: Float) {
        path.lineTo(x, y)
        lastPosition = Pair(x, y)
    }

    fun drawOn(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    fun available(x: Float, y: Float) = lastPosition != Pair(x, y)
}
