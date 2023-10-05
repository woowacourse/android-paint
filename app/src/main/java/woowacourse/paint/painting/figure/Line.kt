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
        path.lineTo(x, y)
    }

    override fun copy(path: Path): Figure = Line(
        path = path,
        paint = paint
    )

    override fun copy(paint: Paint): Figure = Line(
        path = path,
        paint = paint
    )

    companion object {

        fun dot(x: Float, y: Float, paint: Paint): Line {
            return Line(
                path = Path().apply {
                    addOval(
                        x - paint.strokeWidth / 2,
                        y - paint.strokeWidth / 2,
                        x + paint.strokeWidth / 2,
                        y + paint.strokeWidth / 2,
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
