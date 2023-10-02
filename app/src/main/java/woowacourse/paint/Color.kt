package woowacourse.paint

enum class Color(val colorRes: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue),
    BLACK(R.color.black),
    ;

    companion object {

        fun valueOf(position: Int): Int = values().find { color ->
            color.ordinal == position
        }?.colorRes ?: BLACK.colorRes
    }
}