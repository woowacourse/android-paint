package woowacourse.paint.ui.brushtype

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.example.domain.BrushType.LINE
import com.example.domain.Coordinate

class Line : Brush {
    override var type = LINE

    override val path = Path()
    override val paint = Paint().apply {
        isAntiAlias = true
        this.color = Color.BLACK
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        strokeWidth = 0f
        xfermode = null
    }

    override fun startDrawing(coordinate: Coordinate) {
        path.moveTo(coordinate.pointX, coordinate.pointY)
    }

    override fun moveDrawing(coordinate: Coordinate) {
        path.lineTo(coordinate.pointX, coordinate.pointY)
    }

    fun doActionUp(coordinate: Coordinate) {
        path.lineTo(coordinate.pointX, coordinate.pointY)
    }
}
