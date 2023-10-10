package woowacourse.paint.model

enum class DrawMode {
    RECT, CIRCLE, LINE, ERASER;

    companion object {
        fun getDefaultDrawMode(): DrawMode = LINE
    }
}
