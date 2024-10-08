package woowacourse.paint.view.shape

import android.graphics.Canvas
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.paint.utils.PaintWrapper

@Parcelize
sealed class BrushShape(
    open val startX: Float,
    open val startY: Float,
    open val paint: PaintWrapper,
    open val strokeWidth: Float,
) : Parcelable {
    abstract fun updatePosition(
        x: Float,
        y: Float,
    )

    abstract fun draw(canvas: Canvas)
}
