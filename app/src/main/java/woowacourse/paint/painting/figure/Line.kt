package woowacourse.paint.painting.figure

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure

class Line(
    override val path: Path,
    override val paint: Paint
) : Figure {

    val length: Float
        get() = PathMeasure(path, false).length

    init {
        paint.style = Paint.Style.STROKE
    }

    override fun begin(x: Float, y: Float) {
        path.moveTo(x, y)
    }

    override fun extend(x: Float, y: Float) {
        with(path) {
            lineTo(x, y)
            checkPoint(x, y)
        }
    }

    override fun copy(path: Path): Figure = Line(
        path = path,
        paint = paint
    )

    override fun copy(paint: Paint): Figure = Line(
        path = path,
        paint = paint
    )

    private fun checkPoint(x: Float, y: Float) {
        path.addCircle(
            x,
            y,
            paint.strokeWidth / VALUE_FOR_MINIMIZING_RADIUS,
            Path.Direction.CW
        )
    }

    companion object {

        private const val VALUE_FOR_MINIMIZING_RADIUS = 1000

        fun dot(x: Float, y: Float, paint: Paint): Line {
            return Line(
                path = Path().apply {
                    addOval(
                        x - paint.strokeWidth / 6,
                        y - paint.strokeWidth / 6,
                        x + paint.strokeWidth / 6,
                        y + paint.strokeWidth / 6,
                        Path.Direction.CW
                    )
                },
                paint = Paint(paint).apply {
                    style = Paint.Style.FILL
                }
            )
        }
    }
}
