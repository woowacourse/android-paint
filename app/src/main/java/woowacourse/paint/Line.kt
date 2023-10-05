package woowacourse.paint

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure

data class Line(
    val path: Path,
    val brush: Paint
) {

    val length: Float = PathMeasure(path, false).length

    companion object {
        fun dot(x: Float, y: Float, brush: Paint): Line {
            return Line(
                path = Path().apply {
                    addOval(
                        x - brush.strokeWidth / 2,
                        y - brush.strokeWidth / 2,
                        x + brush.strokeWidth / 2,
                        y + brush.strokeWidth / 2,
                        Path.Direction.CW
                    )
                },
                brush = Paint(brush).apply {
                    style = Paint.Style.FILL
                }
            )
        }
    }
}
