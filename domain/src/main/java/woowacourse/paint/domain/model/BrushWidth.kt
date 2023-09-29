package woowacourse.paint.domain.model

@JvmInline
value class BrushWidth(val value: Float = 30f) {
    init {
        require(value in MIN_WIDTH..MAX_WIDTH) { "굵기가 범위를 벗어났습니다" }
    }

    companion object {
        const val MIN_WIDTH = 1f
        const val MAX_WIDTH = 100f
    }
}
