package woowacourse.paint.model.drawingEngine.path

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.model.drawingEngine.PathDrawingEngine

data class PathEraserDrawingEngine(
    override val paint: Paint = Paint(),
    override val path: Path = Path(),
) : PathDrawingEngine() {

    init {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }
}
