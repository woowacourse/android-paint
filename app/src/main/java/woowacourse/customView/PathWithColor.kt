package woowacourse.customView

import android.graphics.Path
import woowacourse.model.BoardColor

data class PathWithColor(
    val path: Path,
    val color: BoardColor,
    val strokeWidth: Float,
)
