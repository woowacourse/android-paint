package woowacourse.paint.ui.brushtype

import android.graphics.Paint
import android.graphics.Path
import com.example.domain.BrushType.CIRCLE

class Circle : BrushType {
    override var type = CIRCLE

    var path = Path()
    private var paint = Paint()

    private var startPointX = START_DEFAULT_COORDINATE
    private var startPointY = START_DEFAULT_COORDINATE

    override fun setupPaint(width: Float, color: Int) {
        path = Path()
        paint = Paint()

        paint.apply {
            this.color = color
            style = Paint.Style.FILL
            strokeWidth = width
            xfermode = null
        }
    }

    override fun startDrawing(pointX: Float, pointY: Float) {
        startPointX = pointX
        startPointY = pointY
    }

    override fun moveDrawing(pointX: Float, pointY: Float) {
        path.reset()
        path.addOval(startPointX, startPointY, pointX, pointY, Path.Direction.CCW)
    }

    override fun getPath(): Path {
        return path
    }

    override fun getPaint(): Paint {
        return paint
    }

    override fun setStrokeWidth(width: Float) {
        paint.strokeWidth = width
    }

    override fun setStrokeColor(color: Int) {
        paint.color = color
    }

    companion object {
        private const val START_DEFAULT_COORDINATE = 0f
    }
}
