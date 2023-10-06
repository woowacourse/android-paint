package woowacourse.paint.model

import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

class BrushEraser : BrushLine() {
    init {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }
}
