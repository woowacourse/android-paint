package woowacourse.paint.ui.brushtype

import android.graphics.Paint
import android.graphics.Path
import com.example.domain.BrushType

interface BrushType {
    var type: BrushType
    val path: Path
    val paint: Paint

    fun startDrawing(pointX: Float, pointY: Float)
    fun moveDrawing(pointX: Float, pointY: Float)
}
