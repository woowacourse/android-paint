package woowacourse.paint.custom.view.model

import android.graphics.Paint
import android.graphics.Path
import com.now.domain.BrushWidth
import woowacourse.paint.presentation.uimodel.BrushColorUiModel

data class CurveLine(
    val path: Path,
    val paint: Paint,
) {
    private var lastX = 0f
    private var lastY = 0f

    init {
        paint.strokeJoin = Paint.Join.ROUND
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
    }

    fun changeStrokeWidth(new: BrushWidth): CurveLine {
        return this.copy(
            path = Path(),
            paint = Paint(this.paint).apply { strokeWidth = new.width },
        )
    }

    fun changeColor(new: BrushColorUiModel): CurveLine {
        return this.copy(
            path = Path(),
            paint = Paint(this.paint).apply { color = new.color },
        )
    }

    fun moveTo(x: Float, y: Float) {
        path.moveTo(x, y)
        lastX = x
        lastY = y
    }

    fun quadTo(x: Float, y: Float) {
        path.quadTo(lastX, lastY, (x + lastX) / 2, (y + lastY) / 2)
        lastX = x
        lastY = y
    }
}
