package woowacourse.paint.presentation.paint.model

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

enum class BrushType {
    PEN,
    RECTANGLE,
    CIRCLE,
    ERASER,
    ;

    private fun isShape(): Boolean = this == RECTANGLE || this == CIRCLE

    fun paintStyle(): Paint.Style {
        return if (isShape()) Paint.Style.FILL else Paint.Style.STROKE
    }

    fun eraserMode(): PorterDuffXfermode? {
        return if (this == ERASER) PorterDuffXfermode(PorterDuff.Mode.CLEAR) else null
    }
}
