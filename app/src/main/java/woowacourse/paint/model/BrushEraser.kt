package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

class BrushEraser(paint: Paint) : BrushLine(paint) {
    init {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }
}
