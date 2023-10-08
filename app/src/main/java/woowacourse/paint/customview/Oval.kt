package woowacourse.paint.customview

import android.graphics.Paint
import android.graphics.Path

class Oval(private val ovalColor: Int) : PaintTool {
    override val stroke: Stroke = Stroke(Path(), initPaint())

    private var startPointX = 0f
    private var startPointY = 0f
    private var currentPointX = 0f
    private var currentPointY = 0f

    private fun initPaint(): Paint {
        return Paint().apply {
            this.color = ovalColor
            style = Paint.Style.FILL
            isAntiAlias = true
        }
    }

    override fun prepare(pointX: Float, pointY: Float) {
        stroke.path.moveTo(pointX, pointY)
        startPointX = pointX
        startPointY = pointY
    }

    override fun use(pointX: Float, pointY: Float) {
        stroke.path.reset()
        currentPointX = pointX
        currentPointY = pointY

        stroke.path.addOval(
            startPointX,
            startPointY,
            currentPointX,
            currentPointY,
            Path.Direction.CW,
        )
    }

}
