package woowacourse.paint

class ColorItem(
    val color: Int,
) {
    fun getArgbColor(): Int {
        return color or 0xFF000000.toInt()
    }
}
