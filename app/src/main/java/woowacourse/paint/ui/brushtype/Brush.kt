package woowacourse.paint.ui.brushtype

import android.graphics.Paint
import android.graphics.Path
import com.example.domain.Coordinate

interface Brush {
    val path: Path
    val paint: Paint

    fun startDrawing(coordinate: Coordinate)
    fun moveDrawing(coordinate: Coordinate)
}
