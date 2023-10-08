package woowacourse.paint.customview

import android.graphics.Paint
import android.graphics.Path

class Pen(private val penColor: Int, private val thickness: Float) : PaintTool {

    override val stroke: Stroke = Stroke(Path(), initPaint())

    private fun initPaint(): Paint {
        return Paint().apply {
            color = penColor
            isAntiAlias = true
            strokeWidth = thickness
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
        }
    }

    override fun prepare(pointX: Float, pointY: Float) {
        stroke.path.moveTo(pointX, pointY)
    }

    override fun use(pointX: Float, pointY: Float) {
        stroke.path.lineTo(pointX, pointY)
    }
}
