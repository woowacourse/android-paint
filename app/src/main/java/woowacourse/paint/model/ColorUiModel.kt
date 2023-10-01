package woowacourse.paint.model

import woowacourse.paint.canvas.PaletteColor

data class ColorUiModel(val color: PaletteColor, val isPicked: Boolean = false)
