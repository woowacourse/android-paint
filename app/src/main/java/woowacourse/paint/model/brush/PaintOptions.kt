package woowacourse.paint.model.brush

import android.graphics.Paint

enum class PaintOptions(val paint: Paint) {
    STROKE(
        Paint().apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            isAntiAlias = true
        },
    ),
    FILLED(Paint().apply { this.style = Paint.Style.FILL }),
}
