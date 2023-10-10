package woowacourse.paint.painting.cache

import woowacourse.paint.painting.figure.Figure

data class PaintingHistory(
    val figures: List<Figure>,
    val action: Action,
) {

    fun invert() = copy(
        action = !action,
    )

    companion object {

        fun added(vararg figures: Figure) = PaintingHistory(
            figures = figures.toList(),
            action = Action.ADD
        )

        fun removed(vararg figures: Figure) = PaintingHistory(
            figures = figures.toList(),
            action = Action.REMOVE
        )
    }
}
