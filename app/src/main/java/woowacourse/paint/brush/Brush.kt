package woowacourse.paint.brush

import android.graphics.Path
import woowacourse.paint.CanvasPaint

sealed class Brush: Path() {

    abstract val paint:CanvasPaint

    abstract fun startDraw(pointX: Float, pointY: Float): Brush

    abstract fun moveBrush(pointX: Float, pointY: Float)
}