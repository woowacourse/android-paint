package woowacourse.paint.painting.figure

import android.graphics.Paint
import android.graphics.Path

data class Rectangle(
    override val path: Path,
    override val paint: Paint
) : Figure {

    private var standardX = INITIAL_POSITION
    private var standardY = INITIAL_POSITION

    init {
        paint.style = Paint.Style.FILL
    }

    override fun begin(x: Float, y: Float) {
        standardX = x
        standardY = y
    }

    override fun extend(x: Float, y: Float) {
        val (left, top, right, bottom) = listOf(
            minOf(standardX, x),
            minOf(standardY, y),
            maxOf(standardX, x),
            maxOf(standardY, y)
        )

        path.reset()
        path.addRect(left, top, right, bottom, Path.Direction.CW)
    }

    override fun copy(path: Path): Figure = Rectangle(
        path = path,
        paint = paint
    )

    override fun copy(paint: Paint): Figure = Rectangle(
        path = path,
        paint = paint
    )

    companion object {

        private const val INITIAL_POSITION = 0f
    }
}
