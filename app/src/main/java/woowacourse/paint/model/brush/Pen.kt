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
        val path = Path().apply { moveTo(x, y) }
        val paint = Paint().apply {
            set(paintInstance)
        }
        previousDrawings.add(path to paint)
    }

    fun drawLine(x: Float, y: Float) {
        previousDrawings.last().first.lineTo(x, y)
    }
}
