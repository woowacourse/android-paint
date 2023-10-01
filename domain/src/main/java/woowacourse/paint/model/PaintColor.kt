package woowacourse.paint.model

data class PaintColor(
    val color: String,
) {
    companion object {
        fun of(color: Int): PaintColor {
            return PaintColor(Integer.toHexString(color))
        }
    }
}
