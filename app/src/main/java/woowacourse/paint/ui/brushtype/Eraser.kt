package woowacourse.paint.ui.brushtype

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import com.example.domain.BrushType.ERASER

class Eraser : BrushType {
    override var type = ERASER

    override val path = Path()
    override val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        strokeWidth = 0f
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    override fun startDrawing(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }

    override fun moveDrawing(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }

    fun doActionUp(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }
}
