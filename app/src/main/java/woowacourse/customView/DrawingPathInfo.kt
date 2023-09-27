package woowacourse.customView

import android.graphics.Path
import androidx.annotation.ColorInt

data class DrawingPathInfo(
    val path: Path,
    @ColorInt val boardColor: Int,
    val strokeWidth: Float,
)
