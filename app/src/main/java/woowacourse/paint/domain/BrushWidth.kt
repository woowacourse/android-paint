package woowacourse.paint.domain

data class BrushWidth(val value: Float) {
    init {
        require(value in range)
    }

    companion object {
        val range: ClosedFloatingPointRange<Float> = 0F..100F
    }
}
