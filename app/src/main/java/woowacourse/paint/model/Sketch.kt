package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint

abstract class Sketch {
    abstract fun draw(canvas: Canvas)

    fun getPaint(paint: Paint): Paint {
        return Paint().apply {
            color = paint.color
            style = Paint.Style.STROKE
            strokeWidth = paint.strokeWidth
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
    }
}
