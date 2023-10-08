package woowacourse.paint.customview.paint.tool

import woowacourse.paint.customview.paint.Painting

sealed interface PaintTool {
    val painting: Painting

    fun prepare(pointX: Float, pointY: Float)
    fun use(pointX: Float, pointY: Float)
}
