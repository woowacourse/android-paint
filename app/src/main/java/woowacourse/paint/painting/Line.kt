package woowacourse.paint.painting

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class Line(
    path: Path = Path(),
    paintColor: Int,
    paintWidth: Float,
) : Painting(
    path = path,
    paint = Paint().apply {
        strokeWidth = paintWidth
        color = paintColor
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        xfermode = null
    },
) {

    override fun onActionDown(x: Float, y: Float) {
        path.moveTo(x, y)
        path.lineTo(x, y)
    }

    override fun onActionMove(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    override fun getInitializedPathPainting(): Painting {
        return Line(Path(), paint.color, paint.strokeWidth)
    }

    override fun drawOnCanvas(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}
