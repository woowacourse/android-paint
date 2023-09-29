package woowacourse.paint.customView

import android.graphics.Paint
import android.graphics.Path

data class DrawingPathInfo(
    val path: Path,
    val paint: Paint,
) {
    fun deepCopy(): DrawingPathInfo {
        return DrawingPathInfo(Path(path), Paint(paint))
    }
}
