package woowacourse.paint.painting.cache

enum class Action {
    REMOVE,
    ADD;

    operator fun not() = if (this == REMOVE) {
        ADD
    } else {
        REMOVE
    }
}
