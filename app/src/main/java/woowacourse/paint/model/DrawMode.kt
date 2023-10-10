package woowacourse.paint.model

enum class DrawMode {
    RECT, Oval, LINE, ERASER;

    companion object {
        fun getDefaultDrawMode(): DrawMode = LINE
    }
}
