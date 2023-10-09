package woowacourse.paint.ui.brushtype

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

interface BrushType {
    fun setupPaint(width: Float = 0F, color: Int = Color.BLACK)
    fun doActionDown(pointX: Float, pointY: Float)
    fun doActionMove(pointX: Float, pointY: Float)
    fun getPath(): Path
    fun getPaint(): Paint
    fun setStrokeWidth(width: Float)
    fun setStrokeColor(color: Int)
}
