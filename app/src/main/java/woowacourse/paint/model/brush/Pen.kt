package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

object Pen : Brush() {
    override fun setStyle() {
        paintInstance.apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }

    fun startDraw(x: Float, y: Float) {
        previousDraw.add(
            Pair(
                Path().apply { moveTo(x, y) },
                Paint().apply {
                    set(paintInstance)
                },
            ),
        )
    }

    fun drawLine(x: Float, y: Float) {
        previousDraw.last().first.lineTo(x, y)
    }
}
