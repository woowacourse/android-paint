package woowacourse.paint

enum class BrushState(private val value: String) {
    PEN("펜"),
    RECTANGLE("직사각형"),
    CIRCLE("원"),
    ERASER("지우개"),
    ;

    companion object {
        fun getBrushState(value: String): BrushState {
            return entries.first { it.value == value }
        }
    }
}
