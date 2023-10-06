package woowacourse.paint.paintingtools

import android.graphics.Paint

class Pen(
    paintColor: Int,
    paintWidth: Float,
) : PaintingTool(
    paintColor = paintColor,
    paintWidth = paintWidth,
) {

    override val paint: Paint = Paint().apply {
        strokeWidth = paintWidth
        color = paintColor
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        xfermode = null
    }

    override fun copy(): PaintingTool {
        return Pen(paint.color, paint.strokeWidth)
    }
}
