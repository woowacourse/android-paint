package woowacourse.paint.ui.brushtype

import android.graphics.Paint
import android.graphics.Path
import com.example.domain.BrushType
import com.example.domain.Coordinate

interface BrushType {
    var type: BrushType
    val path: Path
    val paint: Paint

    fun startDrawing(coordinate: Coordinate)
    fun moveDrawing(coordinate: Coordinate)
}
