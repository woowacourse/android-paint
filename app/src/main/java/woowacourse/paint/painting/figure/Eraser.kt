package woowacourse.paint.painting.figure

import android.graphics.Paint
import android.graphics.Path

class Eraser(
    override val path: Path,
    override val paint: Paint
) : Figure {

    val isOverlapped: (otherPath: Path) -> (Boolean) = { otherPath ->
        val region = Path(path).apply {
            close()
        }
        region.op(
            Path(otherPath).apply {
                close()
            },
            Path.Op.INTERSECT
        )

        !region.isEmpty
    }

    init {
        paint.style = Paint.Style.FILL
    }

    override fun begin(x: Float, y: Float) {
        path.moveTo(x, y)
    }

    override fun extend(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    override fun copy(path: Path): Figure = Eraser(
        path = path,
        paint = paint
    )

    override fun copy(paint: Paint): Figure = Eraser(
        path = path,
        paint = paint
    )
}
