package woowacourse.paint.action

import androidx.annotation.ColorRes
import woowacourse.paint.model.DrawingMode

interface PaintActionHandler {
    fun changeColorRes(
        @ColorRes colorRes: Int,
    )

    fun changeDrawingMode(drawingMode: DrawingMode)

    fun clearDrawings()

    fun undoDrawing()
}
