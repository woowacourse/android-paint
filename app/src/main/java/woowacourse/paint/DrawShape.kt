package woowacourse.paint

import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF

sealed class DrawShape {
    abstract val strokeWidth: Float
    abstract val color: Int
    abstract val style: Paint.Style

    data class Line(
        val path: Path,
        override val strokeWidth: Float,
        override val color: Int,
        override val style: Paint.Style = Paint.Style.STROKE,
    ) :
        DrawShape()

    data class Rectangle(
        val rect: RectF,
        override val strokeWidth: Float,
        override val color: Int,
        override val style: Paint.Style = Paint.Style.STROKE,
        ) : DrawShape()

    data class Circle(
        val centerX: Float,
        val centerY: Float,
        val radius: Float,
        override val strokeWidth: Float,
        override val color: Int,
        override val style: Paint.Style = Paint.Style.STROKE,
        ) : DrawShape()
}
