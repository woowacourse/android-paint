package woowacourse.paint.presentation.palette.listener

import woowacourse.paint.presentation.paint.model.ColorUiModel

interface ColorPaletteListener {
    fun onSelectColor(colorUiModel: ColorUiModel)
}
