package woowacourse.paint.domain

data class BrushColor(val color: Int) {
    init {
        require(color in paintColors)
    }

    companion object {
        val paintColors: List<Int> = listOf(
            0xFFFF0000.toInt(),
            0xFFFF8000.toInt(),
            0xFFFFFF00.toInt(),
            0xFF00FF00.toInt(),
            0xFF0000FF.toInt()
        )
    }
}
