package woowacourse.paint.ui.glocanvas.paint

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

object EraserPaint : DrawingPaint() {
    override val paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        isAntiAlias = true
    }
}
