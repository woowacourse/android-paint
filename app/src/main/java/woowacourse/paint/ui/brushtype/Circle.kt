package woowacourse.paint.ui.brushtype

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.example.domain.Coordinate

class Circle : Brush {
    override val path = Path()
    override val paint = Paint().apply {
        strokeWidth = 0f
        this.color = Color.BLACK
        style = Paint.Style.FILL
        xfermode = null
    }

    private var startPointX = START_DEFAULT_COORDINATE
    private var startPointY = START_DEFAULT_COORDINATE

    override fun startDrawing(coordinate: Coordinate) {
        startPointX = coordinate.pointX
        startPointY = coordinate.pointY
    }

    override fun moveDrawing(coordinate: Coordinate) {
        path.reset()
        path.addOval(
            startPointX,
            startPointY,
            coordinate.pointX,
            coordinate.pointY,
            Path.Direction.CCW,
        )
    }

    companion object {
        private const val START_DEFAULT_COORDINATE = 0f
    }
}
