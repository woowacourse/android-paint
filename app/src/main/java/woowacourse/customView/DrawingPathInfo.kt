package woowacourse.customView

import android.graphics.Path
import androidx.annotation.ColorInt

data class DrawingPathInfo(
    val path: Path,
    @ColorInt val color: Int,
    val strokeWidth: Float,
)
