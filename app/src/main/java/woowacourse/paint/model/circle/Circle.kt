package woowacourse.paint.model.circle

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import woowacourse.paint.model.Sketch

data class Circle(
    private var _center: Center = Center(0f, 0f),
    private var _radius: Float = 0f,
    private var _color: Int = Color.BLACK,
    private var _strokeWidth: Float = 0f,
) : Sketch() {
    val center get() = _center
    val radius get() = _radius
    val color get() = _color
    val strokeWidth get() = _strokeWidth

    private val paint get() =
        Paint().apply {
            color = this@Circle._color
            style = Paint.Style.STROKE
            strokeWidth = this@Circle._strokeWidth
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }

    fun changeProperty(
        center: Center = this._center,
        radius: Float = this._radius,
        color: Int = this._color,
        strokeWidth: Float = this._strokeWidth,
    ) {
        this._center = center
        this._radius = radius
        this._color = color
        this._strokeWidth = strokeWidth
    }


    override fun draw(canvas: Canvas) {
        canvas.drawCircle(_center.x, _center.y, _radius, paint)
    }
}
