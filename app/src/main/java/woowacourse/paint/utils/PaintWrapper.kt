package woowacourse.paint.utils

import android.graphics.Paint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaintWrapper(
    val color: Int,
    val strokeWidth: Float,
    val style: Paint.Style,
    val isAntiAlias: Boolean,
    val strokeJoin: Paint.Join,
) : Parcelable {
    fun toPaint(): Paint {
        return Paint().apply {
            color = this@PaintWrapper.color
            strokeWidth = this@PaintWrapper.strokeWidth
            style = this@PaintWrapper.style
            isAntiAlias = this@PaintWrapper.isAntiAlias
            strokeJoin = this@PaintWrapper.strokeJoin
        }
    }

    companion object {
        fun from(paint: Paint): PaintWrapper {
            return PaintWrapper(
                paint.color,
                paint.strokeWidth,
                paint.style,
                paint.isAntiAlias,
                paint.strokeJoin,
            )
        }
    }
}
