package woowacourse.paint.ui.glocanvas.paint

import android.graphics.Paint

object PenPaint : DrawingPaint() {
    override val paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        isAntiAlias = true
    }
}
