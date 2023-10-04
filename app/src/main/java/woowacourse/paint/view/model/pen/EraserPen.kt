package woowacourse.paint.view.model.pen

import android.graphics.Path
import android.graphics.RectF
import woowacourse.paint.view.model.pen.ink.Ink

class EraserPen(
    override val ink: Ink = Ink(),
    val requestPaths: () -> List<Path>,
    val removePathAt: (Int) -> Unit
) : Pen {
    override fun startPaint(pointX: Float, pointY: Float) {
        removePath(pointX, pointY)
    }

    override fun movePaint(pointX: Float, pointY: Float) {
        removePath(pointX, pointY)
    }

    override fun cacheCurrentPaint() {
    }

    override fun setStrokeWidth(strokeWidth: Float) {
    }

    override fun setColor(color: Int) {
    }

    private fun removePath(pointX: Float, pointY: Float) {
        requestPaths().forEachIndexed { index, path ->
            val bounds = RectF()
            path.computeBounds(bounds, false)
            if (bounds.contains(pointX, pointY)) {
                removePathAt(index)
                return
            }
        }
    }
}
