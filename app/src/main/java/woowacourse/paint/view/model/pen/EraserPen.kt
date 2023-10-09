package woowacourse.paint.view.model.pen

import android.graphics.Path
import android.graphics.RectF

class EraserPen(
    val requestPaths: () -> List<Path>,
    val removePathAt: (Int) -> Unit
) : Pen {
    override fun startPaint(pointX: Float, pointY: Float) {
        removePath(pointX, pointY)
    }

    override fun movePaint(pointX: Float, pointY: Float) {
        removePath(pointX, pointY)
    }

    private fun removePath(pointX: Float, pointY: Float) {
        val paths = requestPaths().reversed()
        paths.forEachIndexed { index, path ->
            val bounds = RectF()
            path.computeBounds(bounds, false)
            if (bounds.contains(pointX, pointY)) {
                removePathAt(paths.size - index - 1)
                return
            }
        }
    }
}
