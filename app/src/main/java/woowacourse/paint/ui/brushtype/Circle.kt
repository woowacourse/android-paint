package woowacourse.paint.ui.brushtype

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.example.domain.BrushType.CIRCLE

class Circle : BrushType {
    override var type = CIRCLE

    override val path = Path()
    override val paint = Paint().apply {
        strokeWidth = 0f
        this.color = Color.BLACK
        style = Paint.Style.FILL
        xfermode = null
    }

    private var startPointX = START_DEFAULT_COORDINATE
    private var startPointY = START_DEFAULT_COORDINATE

    override fun startDrawing(pointX: Float, pointY: Float) {
        startPointX = pointX
        startPointY = pointY
    }

    override fun moveDrawing(pointX: Float, pointY: Float) {
        path.reset()
        path.addOval(startPointX, startPointY, pointX, pointY, Path.Direction.CCW)
    }

    companion object {
        private const val START_DEFAULT_COORDINATE = 0f
    }
}
