package woowacourse.paint.custom.model

import android.graphics.Canvas

class CurveLines {
    private val lines = mutableListOf<CurveLine>()

    fun add(curveLine: CurveLine) {
        lines.add(curveLine)
    }

    fun draw(canvas: Canvas) {
        lines.forEach {
            canvas.drawPath(it.path, it.paint)
        }
    }
}
