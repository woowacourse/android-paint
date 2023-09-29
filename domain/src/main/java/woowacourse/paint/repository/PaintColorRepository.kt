package woowacourse.paint.repository

import woowacourse.paint.model.PaintColor

interface PaintColorRepository {
    fun getAllPaintColors(): List<PaintColor>
}
