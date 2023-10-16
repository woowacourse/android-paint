package woowacourse.paint.ui.brushtype

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import com.example.domain.Coordinate

class Eraser : Brush {
    override val path = Path()
    override val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        strokeWidth = 0f
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
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
