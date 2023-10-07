package woowacourse.paint.view.model.pen

import android.graphics.Path
import woowacourse.paint.graphics.AutoSortedRect
import woowacourse.paint.view.model.pen.ink.Ink

class RectPen(
    override val onAddInk: (Ink) -> Unit = { _ -> }
) : FigurePen(onAddInk = onAddInk) {
    override fun draw(pointX: Float, pointY: Float) {
        ink.path.addRect(
            AutoSortedRect(lastPointX, lastPointY, pointX, pointY).toRectF(), Path.Direction.CCW
        )
    }
}
