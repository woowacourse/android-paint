package woowacourse.paint

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class Line(
    val path: Path,
    val color: Int,
    val width: Float,
) {
    private val paint = Paint().apply {
        color = this@Line.color
        style = Paint.Style.STROKE
        strokeWidth = this@Line.width
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }

    fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}
