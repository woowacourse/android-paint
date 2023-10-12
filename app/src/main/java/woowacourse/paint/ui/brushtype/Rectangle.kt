package woowacourse.paint.ui.brushtype

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.example.domain.BrushType.RECTANGLE

class Rectangle : BrushType {
    override var type = RECTANGLE

    private val path = Path()
    override var paint = Paint().apply {
        this.color = Color.BLACK
        style = Paint.Style.FILL
        strokeWidth = 0f
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
        path.addRect(startPointX, startPointY, pointX, pointY, Path.Direction.CCW)
    }

    override fun getPath(): Path {
        return path
    }

    companion object {
        private const val START_DEFAULT_COORDINATE = 0f
    }
}
