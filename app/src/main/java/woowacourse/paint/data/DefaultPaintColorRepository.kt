package woowacourse.paint.data

import woowacourse.paint.model.PaintColor
import woowacourse.paint.repository.PaintColorRepository

class DefaultPaintColorRepository : PaintColorRepository {
    private val paintColors = listOf(
        PaintColor("#FF0000"),
        PaintColor("#FFA500"),
        PaintColor("#FFFF00"),
        PaintColor("#008000"),
        PaintColor("#0000FF"),
    )

    override fun getAllPaintColors(): List<PaintColor> = paintColors
}
