package woowacourse.paint.ui.brushtype

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

class Eraser : BrushType {
    private var path = Path()
    private var paint = Paint()

    override fun setupPaint(width: Float, color: Int) {
        path = Path()
        paint = Paint()

        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = width
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    override fun doActionDown(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }

    override fun doActionMove(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }

    fun doActionUp(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
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
}
