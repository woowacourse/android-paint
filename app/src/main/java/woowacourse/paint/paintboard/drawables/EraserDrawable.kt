package woowacourse.paint.paintboard.drawables

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.view.MotionEvent
import woowacourse.paint.entity.Point
import woowacourse.paint.entity.Points
import woowacourse.paint.paintboard.common.Brush
import woowacourse.paint.paintboard.common.EraserState
import woowacourse.paint.util.point

class EraserDrawable(
    points: Points,
    brush: Brush.EraserBrush,
    private val eraseStroke: (point: Point) -> Unit,
) : BrushDrawable {
    private val eraserState: EraserState = brush.mode
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
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = brush.width.toFloat()
    }

    override fun action(event: MotionEvent) {
        if (eraserState == EraserState.PATH) return eraseStroke(event.point)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> start(event.point)
            MotionEvent.ACTION_MOVE -> drawing(event.point)
            MotionEvent.ACTION_UP -> drawing(event.point)
        }
    }

    private fun start(point: Point) {
        points.add(point)
        path.moveTo(point.x, point.y)
        path.lineTo(point.x, point.y)
    }

    private fun drawing(point: Point) {
        points.add(point)
        path.lineTo(point.x, point.y)
    }

    override fun draw(canvas: Canvas) {
        if (eraserState == EraserState.PATH) return
        canvas.drawPath(path, paint)
    }

    override fun contain(point: Point): Boolean {
        return false
    }
}
