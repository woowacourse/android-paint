import android.graphics.Path
import android.graphics.RectF

sealed class DrawShape {
    abstract val strokeWidth: Float
    abstract val color: Int

    data class Line(val path: Path, override val strokeWidth: Float, override val color: Int) : DrawShape()

    data class Rectangle(val rect: RectF, override val strokeWidth: Float, override val color: Int) : DrawShape()

    data class Circle(val centerX: Float, val centerY: Float, val radius: Float, override val strokeWidth: Float, override val color: Int, val isEraser: Boolean = false) : DrawShape()
}
