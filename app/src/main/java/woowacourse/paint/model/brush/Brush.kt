package woowacourse.paint.model.brush

import android.graphics.Color
import android.graphics.Paint

sealed class Brush : BrushSetting {
    val paint get() = paintInstance

    companion object {
        val paintInstance = Paint().apply {
            color = Color.RED
        }
    }
}
