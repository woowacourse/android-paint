package woowacourse.paint.custom.model

import android.graphics.Paint
import android.graphics.Path
import com.now.domain.BrushWidth
import woowacourse.paint.presentation.uimodel.BrushColorUiModel

data class CurveLine(
    val path: Path,
    val paint: Paint,
) {
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
}
