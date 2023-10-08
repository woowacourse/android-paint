package woowacourse.paint.customview

sealed interface PaintTool {
    val painting: Painting

    fun prepare(pointX: Float, pointY: Float)
    fun use(pointX: Float, pointY: Float)
}
