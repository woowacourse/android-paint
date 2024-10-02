package woowacourse.paint.brush

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class Line(
    val path: Path = Path(),
    val paint: Paint = Paint(),
) {
    private var linePath = path
    private var linePaint = paint
    private var endX = DEFAULT_POINT
    private var endY = DEFAULT_POINT

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
    }

    fun move(
        x: Float,
        y: Float,
    ) {
        endX = x
        endY = y
        path.moveTo(endX, endY)
    }

    fun quad(
        x: Float,
        y: Float,
    ) {
        path.quadTo(endX, endY, (x + endX) / 2, (y + endY) / 2)
        endX = x
        endY = y
    }

    companion object {
        private const val DEFAULT_POINT = 0f
    }
}
