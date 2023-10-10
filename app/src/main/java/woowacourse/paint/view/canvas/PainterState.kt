package woowacourse.paint.view.canvas

import woowacourse.paint.view.palette.color.PaletteColor
import woowacourse.paint.view.palette.shape.PaletteShape

data class PainterState(
    val paletteColor: PaletteColor = PaletteColor.values().first(),
    val paletteShape: PaletteShape = PaletteShape.values().first(),
    val thickness: Float = 0F,
)
