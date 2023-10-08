package woowacourse.paint.customview.paint.tool

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.customview.paint.Painting

class Pen(private val penColor: Int, private val thickness: Float) : PaintTool {

    override val painting: Painting = Painting(Path(), initPaint())

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
        painting.path.moveTo(pointX, pointY)
    }

    override fun use(pointX: Float, pointY: Float) {
        painting.path.lineTo(pointX, pointY)
    }
}
