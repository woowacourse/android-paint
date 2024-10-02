package woowacourse.paint.model

enum class ColorItem(
    val color: Int,
) {
    RED(0xCD2701),
    ORANGE(0xFF7C2A),
    YELLOW(0xFFFF00),
    GREEN(0x0D9A00),
    BLUE(0x2601A8),
    ;

    fun getArgbColor(): Int {
        return color or 0xFF000000.toInt()
    }
}
