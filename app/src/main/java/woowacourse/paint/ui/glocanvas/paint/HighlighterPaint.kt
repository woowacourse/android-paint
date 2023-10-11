package woowacourse.paint.ui.glocanvas.paint

import android.graphics.Paint

object HighlighterPaint : DrawingPaint() {
    override val paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.SQUARE
        strokeJoin = Paint.Join.BEVEL
        isAntiAlias = true
    }
}
