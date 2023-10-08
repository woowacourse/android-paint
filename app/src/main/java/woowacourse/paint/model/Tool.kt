package woowacourse.paint.model

enum class Tool(val toolName: String) {
    PEN("펜"),
    RECTANGLE("직사각형"),
    CIRCLE("원"),
    ERASER("지우개"),
    ;


    companion object {

        fun valueOf(position: Int): Tool = Tool.values().find { tool ->
            tool.ordinal == position
        } ?: PEN
    }
}