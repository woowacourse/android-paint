package woowacourse.paint.model

import woowacourse.paint.canvas.CustomColor

data class ColorUiModel(val color: CustomColor, val isPicked: Boolean = false)
