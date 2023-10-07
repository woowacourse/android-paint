package woowacourse.paint.painting.figure

import android.graphics.Paint
import android.graphics.Path

class Circle(
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
        path.reset()
        path.addOval(
            standardX - x / 4,
            standardY - y / 4,
            standardX + x / 4,
            standardY + y / 4,
            Path.Direction.CW
        )
    }

    override fun copy(path: Path): Figure = Circle(
        path = path,
        paint = paint
    )

    override fun copy(paint: Paint): Figure = Circle(
        path = path,
        paint = paint
    )

    companion object {

        private const val INITIAL_POSITION = 0f
    }
}
