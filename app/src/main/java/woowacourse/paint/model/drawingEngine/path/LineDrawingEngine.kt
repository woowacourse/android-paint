package woowacourse.paint.model.drawingEngine.path

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.model.drawingEngine.PathDrawingEngine

data class LineDrawingEngine(
    override val paint: Paint = Paint(),
    override val path: Path = Path(),
) : PathDrawingEngine()
