package woowacourse.paint.data

import woowacourse.paint.model.PaintColor
import woowacourse.paint.repository.PaintColorRepository

class DefaultPaintColorRepository : PaintColorRepository {
    private var paintColor = PaintColor("#FF0000")
    private val paintColors = listOf(
        PaintColor("#FF0000"),
        PaintColor("#FFA500"),
        PaintColor("#FFFF00"),
        PaintColor("#008000"),
        PaintColor("#0000FF"),
    )

    override fun getPaintColor(): PaintColor {
        return paintColor
    }

    override fun setPaintColor(paintColor: PaintColor) {
        this.paintColor = paintColor
    }

    override fun getAllPaintColors(): List<PaintColor> = paintColors
}
