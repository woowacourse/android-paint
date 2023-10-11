package woowacourse.paint.model

enum class DrawMode {
    BRUSH,
    SQUARE,
    CIRCLE,
    ERASER,
    ;

    companion object {
        val DEFAULT_MODE: DrawMode
            get() = BRUSH
    }
}
