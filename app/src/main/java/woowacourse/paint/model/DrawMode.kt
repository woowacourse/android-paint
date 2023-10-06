package woowacourse.paint.model

enum class DrawMode {
    BRUSH,
    SQUARE,
    CIRCLE,
    ;

    companion object {
        val DEFAULT_MODE: DrawMode
            get() = BRUSH
    }
}
