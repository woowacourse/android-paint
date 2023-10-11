package woowacourse.paint.ui.glocanvas.paint

import android.graphics.Paint

object ShapePaint : DrawingPaint() {
    override val paint: Paint = Paint().apply {
        isAntiAlias = true
    }
}
