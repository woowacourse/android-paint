package woowacourse.paint.paintboard.drawables

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.view.MotionEvent.ACTION_UP
import woowacourse.paint.entity.Point
import woowacourse.paint.entity.Points
import woowacourse.paint.paintboard.common.Brush
import woowacourse.paint.util.point

class LineDrawable(
    points: Points,
    brush: Brush.LineBrush,
) : BrushDrawable {
    private val points = mutableListOf<Point>().apply { addAll(points.value) }
    private val path = Path().apply {
        points.value.forEachIndexed { index, point ->
            if (index == 0) {
                moveTo(point.x, point.y)
            } else {
                lineTo(point.x, point.y)
            }
        }
    }
    private val paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = brush.width.toFloat()
        color = brush.color
    }

    override fun action(event: MotionEvent) {
        when (event.action) {
            ACTION_DOWN -> start(event.point)
            ACTION_MOVE -> drawing(event.point)
            ACTION_UP -> drawing(event.point)
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    private fun start(point: Point) {
        points.add(point)
        path.moveTo(point.x, point.y)
    }

    private fun drawing(point: Point) {
        points.add(point)
        path.lineTo(point.x, point.y)
    }

    override fun contain(point: Point): Boolean {
        val bounds = getBounds()
        return bounds.contains(point.x, point.y)
    }

    private fun getBounds(): RectF {
        return RectF().apply {
            path.computeBounds(this, true)
        }
    }
}
