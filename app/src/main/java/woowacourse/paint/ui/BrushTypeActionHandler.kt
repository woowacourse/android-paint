package woowacourse.paint.ui

import woowacourse.paint.model.BrushType

interface BrushTypeActionHandler {
    fun changeBrushType(brushType: BrushType)
}
