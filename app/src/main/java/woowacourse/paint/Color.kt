package woowacourse.paint

enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, BLACK,
    ;

    companion object {

        fun valueOf(position: Int): Color = values().find { color ->
            color.ordinal == position
        } ?: BLACK
    }
}