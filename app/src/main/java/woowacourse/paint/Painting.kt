package woowacourse.paint

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.tool.Tool

class Painting(
    val tool: Tool,
    val paint: Paint,
) {
    fun startDraw(pointX: Float, pointY: Float) {
        tool.startDraw(pointX, pointY)
    }

    fun onDraw(pointX: Float, pointY: Float) {
        tool.onDraw(pointX, pointY)
    }

    fun drawPath(canvas: Canvas) {
        tool.drawPath(canvas, paint)
    }

    fun changeSize(value: Float) {
        paint.strokeWidth = value
    }

    fun changeColor(value: Int) {
        paint.color = value
    }

    fun copy(): Painting {
        return Painting(tool.copy(), Paint(paint))
    }

    fun changeTool(tool: Tool, style: Paint.Style, isEraser: Boolean = false): Painting {
        when (isEraser) {
            true -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            else -> paint.xfermode = null
        }
        paint.style = style
        return Painting(tool, paint)
    }
}
