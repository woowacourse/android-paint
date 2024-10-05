package woowacourse.paint.paintcanvas.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class PathShape(val path: Path = Path()) : Shape {
    override fun onActionDown(
        pointX: Float,
        pointY: Float,
    ) {
        path.moveTo(pointX, pointY)
    }

    override fun onActionMove(
        pointX: Float,
        pointY: Float,
    ) {
        path.lineTo(pointX, pointY)
    }

    override fun onActionUp(
        pointX: Float,
        pointY: Float,
    ) {
        path.lineTo(pointX, pointY)
    }

    override fun draw(
        canvas: Canvas,
        paint: Paint,
    ) {
        paint.apply { style = Paint.Style.STROKE }
        canvas.drawPath(path, paint)
    }
}
