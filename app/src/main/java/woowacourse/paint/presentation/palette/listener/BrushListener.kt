package woowacourse.paint.presentation.palette.listener

import woowacourse.paint.presentation.paint.model.BrushType

interface BrushListener {
    fun onChangeBrushType(brushType: BrushType)
}
