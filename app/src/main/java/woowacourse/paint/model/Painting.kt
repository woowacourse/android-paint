package woowacourse.paint.model

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.annotation.ColorRes
import woowacourse.paint.R
import woowacourse.paint.customview.PaintBoard
import woowacourse.paint.shape.Line
import woowacourse.paint.shape.Shape

class Painting(
    private val shape: Shape = Line(),
    val paint: Paint,
) {
    fun startDraw(pointX: Float, pointY: Float) {
        shape.startDraw(pointX, pointY)
    }

    fun onDraw(pointX: Float, pointY: Float) {
        shape.onDraw(pointX, pointY)
    }

    fun drawPath(canvas: Canvas) {
        shape.drawPath(canvas, paint)
    }

    fun changeSize(value: Float) {
        paint.strokeWidth = value
    }

    fun changeColor(value: Int) {
        paint.color = value
    }

    fun copy(): Painting {
        return Painting(shape.copy(), Paint(paint))
    }

    fun changeShape(shape: Shape, style: Paint.Style, isEraser: Boolean = false): Painting {
        when (isEraser) {
            true -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            else -> paint.xfermode = null
        }
        paint.style = style
        return Painting(shape, paint)
    }

    companion object {
        @ColorRes
        private val DEFAULT_COLOR = R.color.red
        fun newInstance(context: Context): Painting {
            return Painting(
                shape = Line(),
                paint = Paint().apply {
                    isAntiAlias = true
                    style = Paint.Style.STROKE
                    strokeWidth = PaintBoard.DEFAULT_SIZE
                    strokeCap = Paint.Cap.ROUND
                    strokeJoin = Paint.Join.ROUND
                    color = context.getColor(DEFAULT_COLOR)
                },
            )
        }
    }
}
