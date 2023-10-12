package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class RectangleDrawble(
    private val path: Path = Path(),
) : CanvasDrawble {

    private var prevX: Float = 0F
    private var prevY: Float = 0F

    /*   init {
           _paint.apply {
               style = Paint.Style.FILL
               xfermode = null
           }
       }*/

    override fun movePath(x: Float, y: Float) {
        prevX = x
        prevY = y
    }

    override fun initPath(x: Float, y: Float) {
        path.reset()
        path.addRect(
            java.lang.Float.min(prevX, x),
            java.lang.Float.min(prevY, y),
            java.lang.Float.max(prevX, x),
            java.lang.Float.max(prevY, y),
            Path.Direction.CW,
        )
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawPath(path, paint)
    }

    override fun newPainting(): CanvasDrawble {
        return RectangleDrawble()
    }
}
