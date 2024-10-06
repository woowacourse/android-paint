package woowacourse.paint.ui

import woowacourse.paint.model.DrawingMode

interface DrawingModeHandler {
    fun onDrawingModeClicked(mode: DrawingMode)
}
