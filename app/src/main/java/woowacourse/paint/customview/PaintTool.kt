package woowacourse.paint.customview

sealed interface PaintTool {
    val stroke: Stroke

    fun prepare(pointX: Float, pointY: Float)
    fun use(pointX: Float, pointY: Float)
}
