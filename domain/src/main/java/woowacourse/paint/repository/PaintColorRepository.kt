package woowacourse.paint.repository

import woowacourse.paint.model.PaintColor

interface PaintColorRepository {

    fun getPaintColor(): PaintColor
    fun setPaintColor(paintColor: PaintColor)
    fun getAllPaintColors(): List<PaintColor>
}
