package woowacourse.paint.model

enum class Tool(val toolName: String) {
    NORMAL_PEN("펜"),
    RECTANGLE_PEN("직사각형"),
    CIRCLE_PEN("원"),
    ERASER("지우개"),
    ;


    companion object {

        fun valueOf(position: Int): Tool = Tool.values().find { tool ->
            tool.ordinal == position
        } ?: NORMAL_PEN
    }
}