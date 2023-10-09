package woowacourse.paint

import woowacourse.paint.domain.model.Brush
import woowacourse.paint.domain.model.BrushColor
import woowacourse.paint.domain.model.BrushType
import woowacourse.paint.domain.model.BrushWidth

object BrushFixture {
    fun getBrush(
        brushColor: BrushColor = BrushColor.RED,
        brushWidth: BrushWidth = BrushWidth(30f),
        brushType: BrushType = BrushType.PEN,
    ) = Brush(color = brushColor, width = brushWidth, type = brushType)
}
