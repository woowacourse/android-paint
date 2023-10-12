package woowacourse.paint.ui.brushtype

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.example.domain.BrushType

interface BrushType {
    var type: BrushType

    fun setupPaint(width: Float = 0F, color: Int = Color.BLACK)
    fun startDrawing(pointX: Float, pointY: Float)
    fun moveDrawing(pointX: Float, pointY: Float)
    fun getPath(): Path
    fun getPaint(): Paint
    fun setStrokeWidth(width: Float)
    fun setStrokeColor(color: Int)
}
