package woowacourse.paint.model

import woowacourse.paint.R

enum class DefaultColor(val colorInt: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue), ;

    companion object {
        fun getList(): List<DefaultColor> = entries
    }
}
