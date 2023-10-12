package woowacourse.paint.model

enum class DrawMode {
    RECT, OVAL, LINE, ERASER;

    companion object {
        fun getDefaultDrawMode(): DrawMode = LINE
    }
}
