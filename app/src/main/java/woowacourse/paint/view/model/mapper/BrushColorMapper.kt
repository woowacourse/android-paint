package woowacourse.paint.view.model.mapper

import woowacourse.paint.domain.BrushColor

object BrushColorMapper {
    fun BrushColor.toModel(): Int = color
}
